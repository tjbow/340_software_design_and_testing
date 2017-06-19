package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.plugin.IGameDao;
import com.group4.shared.plugin.IPersistencePlugin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SQLiteGameDaoTests {

    private SQLiteTransaction transaction;
    private IGameDao gameDao;

    @Before
    public void setUp()
    {
        transaction = new SQLiteTransaction();
        this.gameDao = new SQLiteGameDao(transaction);
    }

    @After
    public void tearDown()
    {
        transaction.closeConnection();

    }

    @Test
    public void testSaveGame()
    {
        gameDao.clear();
        Game testGame = new Game("testGame", 4);
        gameDao.saveGame(testGame);
    }

    @Test
    public void testDeleteGame()
    {
        gameDao.clear();
        Game testGame = new Game("testGame", 5);
        Game testGame2 = new Game("testGame2", 2);
        gameDao.saveGame(testGame);
        gameDao.saveGame(testGame2);
        gameDao.deleteGame(testGame);
    }

    @Test
    public void testGetGameList()
    {
        gameDao.clear();
        Game testGame = new Game("testGame", 5);
        Game testGame2 = new Game("testGame2", 2);
        gameDao.saveGame(testGame);
        gameDao.saveGame(testGame2);
        GameList gameList = gameDao.getGames();
        assertNotNull(gameList.getGameByName("testGame"));
        assertNotNull(gameList.getGameByName("testGame2"));
        assertTrue(gameList.getGameList().size() == 2);
    }

    @Test
    public void testClear()
    {
        gameDao.clear();
    }

}
