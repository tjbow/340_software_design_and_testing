package plugin;


import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CUpdatePlayersCommandData;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.shared.plugin.IGameDao;
import com.group4.shared.plugin.IPersistencePlugin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SQLiteDatabaseTests {

    private IPersistencePlugin sqliteDatabase;

    @Before
    public void setUp()
    {
        this.sqliteDatabase = new SQLiteDatabase();
        sqliteDatabase.clear();
    }

    @After
    public void tearDown()
    {
        sqliteDatabase = null;
    }

    @Test
    public void saveGameTest()
    {
        Game game = new Game("awesomegame", 3);

        sqliteDatabase.saveGame(game);
    }

    @Test
    public void updateCommandsTest()
    {
        Game game = new Game("awesomegame", 3);

        sqliteDatabase.saveGame(game);

        List<Command> commands = new ArrayList<>();

        Player player = new Player(new User("testplayer1", "pwd"));
        Player player1 = new Player(new User("testplayer2", "pwd"));

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);

        CUpdatePlayersCommandData data = new CUpdatePlayersCommandData();
        data.setType("updateplayers");
        data.setPlayerData(players);

        commands.add(data);

        sqliteDatabase.updateCommands("awesomegame", commands);
    }

    @Test
    public void saveUsersTest()
    {
        List<User> users = new ArrayList<>();

        users.add(new User("testplayer1", "pwd"));
        users.add(new User("testplayer2", "pwd"));
        users.add(new User("testplayer3", "pwd"));
        users.add(new User("testplayer4", "pwd"));

        sqliteDatabase.saveUsers(users);
    }

    @Test
    public void getGamesTest()
    {
        Game game1 = new Game("game1", 3);
        Game game2 = new Game("game2", 5);

        game1.setCommandList(new CommandList());
        //game now has an empty command list
        sqliteDatabase.saveGame(game1);
        sqliteDatabase.saveGame(game2);


        //now query for gameList
        GameList gameList = sqliteDatabase.getGames();

        assertTrue(gameList.getGameList().size() == 2);
    }

    @Test
    public void getUsersTest()
    {
        List<User> users = new ArrayList<>();

        users.add(new User("testplayer1", "pwd"));
        users.add(new User("testplayer2", "pwd"));
        users.add(new User("testplayer3", "pwd"));
        users.add(new User("testplayer4", "pwd"));

        sqliteDatabase.saveUsers(users);

        assertTrue(sqliteDatabase.getUsers().size() == 4);
    }

    @Test
    public void getCommandsTest()
    {

        List<Command> commands = new ArrayList<>();

        Player player = new Player(new User("testplayer1", "pwd"));
        Player player1 = new Player(new User("testplayer2", "pwd"));

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);

        CUpdatePlayersCommandData data = new CUpdatePlayersCommandData();
        data.setType("updateplayers");
        data.setPlayerData(players);

        commands.add(data);
        sqliteDatabase.updateCommands("game1", commands);
        List<Command> commandList = sqliteDatabase.getCommands("game1");
        assertNotNull(commandList);
    }

    @Test
    public void deleteGameTest()
    {
        Game game = new Game("awesomegame", 3);
        Game game1 = new Game("gameToDelete", 5);
        sqliteDatabase.saveGame(game);
        sqliteDatabase.saveGame(game1);
        sqliteDatabase.deleteGame(game1);
        assertTrue(sqliteDatabase.getGames().getGameList().size() == 1);
    }

    @Test
    public void testClear()
    {

    }
}
