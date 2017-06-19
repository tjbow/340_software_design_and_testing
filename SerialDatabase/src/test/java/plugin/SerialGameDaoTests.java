package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.shared.plugin.IPersistencePlugin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialGameDaoTests
{
    private IPersistencePlugin mDatabase;

    @Before
    public void setUp()
    {
        mDatabase = new SerialDatabase();
    }

    @After
    public void tearDown()
    {
        mDatabase = null;
    }

    @Test
    public void testSaveGame()
    {
        Game game = new Game("testgame", 5);
        Game game1 = new Game("testgame1", 5);
        Game game2 = new Game("testgame2", 5);
        Game game3 = new Game("testgame3", 5);
        Game game4 = new Game("testgame4", 5);
        Game game5 = new Game("testgame5", 5);

        game.addPlayer(new Player(new User("testplayer", "pwd")));
        game.addPlayer(new Player(new User("otherplayer", "pwd")));


        mDatabase = new SerialDatabase();

        mDatabase.saveGame(game);
//        mDatabase.saveGame(game1);
//        mDatabase.saveGame(game2);
//        mDatabase.saveGame(game3);
//        mDatabase.saveGame(game4);
//        mDatabase.saveGame(game5);
    }

    @Test
    public void testGetGames()
    {
        GameList gameList = null;

        mDatabase = new SerialDatabase();

        gameList = mDatabase.getGames();

        for(Game game : gameList.getGameList())
        {
            System.out.println(game.getGameName());
        }
    }

    @Test
    public void testGetPlayers()
    {
        String gameName = "testgame";

        mDatabase.getGames();
    }

    @Test
    public void testClear()
    {
        mDatabase = new SerialDatabase();

        mDatabase.clear();
    }
}
