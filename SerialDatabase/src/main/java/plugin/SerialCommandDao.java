package plugin;

import com.group4.shared.command.Command;
import com.group4.shared.plugin.ICommandDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialCommandDao implements ICommandDao
{

    @Override
    public void updateCommands(String gameName, List<Command> commands)
    {
        try
        {
            // write object to file
            FileOutputStream fos = new FileOutputStream(SerialUtils.COMMANDS_DIRECTORY + gameName + "_commands.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(commands);
            oos.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Command> getCommands(String gameName)
    {

        File dir = new File(SerialUtils.COMMANDS_DIRECTORY);
        File[] directoryListing = dir.listFiles();
        List<Command> commands = null;

        if (directoryListing != null)
        {
            for (File commandListFile : directoryListing)
            {
                if (commandListFile.toString().equals(gameName + "_commands.ser"))
                {
                    try
                    {
                        FileInputStream fis = new FileInputStream(commandListFile);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        commands = (List<Command>) ois.readObject();
                        ois.close();
                    }
                    catch (FileNotFoundException e)
                    {
                        //e.printStackTrace();
                        System.out.println("No commands to load.");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(commands == null)
        {
            return new ArrayList<Command>();
        }

        return commands;
    }

    @Override
    public void clear()
    {

    }
}
