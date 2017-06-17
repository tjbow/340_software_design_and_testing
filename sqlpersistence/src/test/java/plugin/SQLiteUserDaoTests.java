package plugin;


import com.group4.shared.Model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SQLiteUserDaoTests {
    private SQLiteTransaction transaction;
    private SQLiteUserDao userDao;

    @Before
    public void setUp()
    {
        transaction = new SQLiteTransaction();
        this.userDao = new SQLiteUserDao(transaction);
        userDao.clear();
    }

    @After
    public void tearDown()
    {
        transaction.closeConnection();
        transaction = null;
        userDao = null;
    }

    @Test
    public void saveUsersTest()
    {
        List<User> users = new ArrayList<>();

        users.add(new User("testplayer1", "pwd"));
        users.add(new User("testplayer2", "pwd"));
        users.add(new User("testplayer3", "pwd"));
        users.add(new User("testplayer4", "pwd"));

        userDao.saveUsers(users);
    }

    @Test
    public void getUsersTest()
    {
        List<User> users = new ArrayList<>();

        users.add(new User("testplayer1", "pwd"));
        users.add(new User("testplayer2", "pwd"));
        users.add(new User("testplayer3", "pwd"));
        users.add(new User("testplayer4", "pwd"));

        userDao.saveUsers(users);

        assertTrue(userDao.getUsers().size() == users.size());
    }
}
