package plugin;

import com.group4.shared.Model.Player;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CUpdatePlayersCommandData;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.plugin.IPersistencePlugin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialCommandDaoTests
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
    public void testUpdateCommands()
    {
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

        mDatabase.updateCommands("testgame.ser", commands);
    }

    @Test
    public void testGetCommands()
    {
        List<ClientCommand> commands = mDatabase.getCommands("testgame.ser");
        System.out.println(commands.size());
    }
}
