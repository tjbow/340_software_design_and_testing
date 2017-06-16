package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.User;
import com.group4.shared.plugin.IUserDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialUserDao implements IUserDao
{
    @Override
    public void saveUsers(List<User> users)
    {
        try
        {
            // write object to file
            FileOutputStream fos = new FileOutputStream(SerialUtils.GAMES_DIRECTORY + "users" + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers()
    {
        File file = new File(SerialUtils.GAMES_DIRECTORY + "users.ser");

        List<User> users = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (List<User>) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void clear()
    {

    }
}
