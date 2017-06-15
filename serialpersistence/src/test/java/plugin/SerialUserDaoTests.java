package plugin;

import com.group4.shared.Model.User;
import com.group4.shared.plugin.IPersistencePlugin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialUserDaoTests
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
    public void testSaveUsers()
    {
        List<User> users = new ArrayList<>();

        users.add(new User("testplayer1", "pwd"));
        users.add(new User("testplayer2", "pwd"));
        users.add(new User("testplayer3", "pwd"));
        users.add(new User("testplayer4", "pwd"));

        mDatabase.saveUsers(users);
    }

    @Test
    public void testGetUsers()
    {
        List<User> users = mDatabase.getUsers();
        System.out.println(users.size());
    }

    @Test
    public void testClear()
    {
        mDatabase.clear();
    }
}
