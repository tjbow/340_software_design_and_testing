package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.plugin.IGameDao;


import java.io.InputStream;
import java.io.Serializable;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SQLiteGameDao extends SQLiteBaseDao implements IGameDao{

    public SQLiteGameDao(SQLiteTransaction transaction)
    {
        super(transaction);
    }

    @Override
    public void saveGame(Game game)
    {
        String saveGameSQL = "INSERT INTO Game " +
                "(GameName, GameSnapshot) values (?,?)";
        List<Object> entries = new ArrayList<>();
        entries.add(game.getGameName());
        entries.add(game);

        update(saveGameSQL, entries);
    }

    @Override
    public GameList getGames()
    {
        String getGamesSQL = "SELECT GameSnapshot FROM Game";
        List<Object> fields = new ArrayList<>();
        List<Class> types = new ArrayList<>();
        types.add(InputStream.class);

        List<Game> gameList = new ArrayList<>();

        List<List<Object>> resultList = query(getGamesSQL, fields, types);
        for (List<Object> results : resultList)
        {
            for (Object result : results)
            {
                if (result.getClass() == Game.class)
                {
                    gameList.add( (Game) result );
                }
            }
        }

        return new GameList( gameList );
    }

    @Override
    public void clear() {
        String dropGameTableSQL = "DROP TABLE IF EXISTS main.Game;\n";
        dropTable(dropGameTableSQL);
        create();
    }

    @Override
    public void deleteGame(Game game)
    {
        String deleteGameSQL = "DELETE FROM Game WHERE GameName = ?";
        List<Object> queryFields = new ArrayList<>();
        queryFields.add( game.getGameName() );

        deleteSingle(deleteGameSQL, queryFields);
    }


    public void create() {
        String createGameTableSQL = "CREATE TABLE IF NOT EXISTS main.Game (\n" +
                "\tGameName TEXT PRIMARY KEY NOT NULL UNIQUE, \n" +
                "\tGameSnapshot BLOB NOT NULL\n" +
                ");";

        createTable(createGameTableSQL);
    }


}
