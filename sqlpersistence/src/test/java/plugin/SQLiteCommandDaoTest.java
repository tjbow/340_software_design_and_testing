package plugin;


import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CUpdatePlayersCommandData;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.plugin.ICommandDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SQLiteCommandDaoTest {
    private SQLiteTransaction transaction;
    private SQLiteCommandDao commandDao;

    @Before
    public void setUp()
    {
        transaction = new SQLiteTransaction();
        this.commandDao = new SQLiteCommandDao(transaction);
    }

    @After
    public void tearDown()
    {
        transaction.closeConnection();
        transaction = null;
        commandDao = null;
    }

    @Test
    public void testUpdateCommands()
    {
        commandDao.clear();
        List<ClientCommand> commands = new ArrayList<>();

        Player player = new Player(new User("testplayer1", "pwd"));
        Player player1 = new Player(new User("testplayer2", "pwd"));

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);

        CUpdatePlayersCommandData data = new CUpdatePlayersCommandData();
        data.setType("updateplayers");
        data.setPlayerData(players);

        commands.add(data);

        commandDao.updateCommands("testGame", commands);
//        List<ClientCommand> commandList = commandDao.getCommands("testGame");
//        assertNotNull(commandList);
    }

    @Test
    public void testGetCommands()
    {
        commandDao.clear();
        List<ClientCommand> commands = new ArrayList<>();

        Player player = new Player(new User("testplayer1", "pwd"));
        Player player1 = new Player(new User("testplayer2", "pwd"));

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);

        CUpdatePlayersCommandData data = new CUpdatePlayersCommandData();
        data.setType("updateplayers");
        data.setPlayerData(players);

        commands.add(data);

        commandDao.updateCommands("testGame", commands);
        List<ClientCommand> commandList = commandDao.getCommands("testGame");
        assertTrue(commandList.size() == 1);
    }

    @Test
    public void testDeleteCommandList()
    {
        commandDao.clear();
        List<ClientCommand> commands = new ArrayList<>();

        Player player = new Player(new User("testplayer1", "pwd"));
        Player player1 = new Player(new User("testplayer2", "pwd"));

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);

        CUpdatePlayersCommandData data = new CUpdatePlayersCommandData();
        data.setType("updateplayers");
        data.setPlayerData(players);

        commands.add(data);

        commandDao.updateCommands("testGame", commands);

        commandDao.deleteCommandList("testGame");
    }

    @Test
    public void clearTest()
    {
        commandDao.clear();
    }


}
