package plugin;

import com.group4.shared.Model.Game.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialUtils
{
    protected static final String GAMES_DIRECTORY = "games/";

    static Game getGameFromFile(File gameFile)
    {
        Game game = null;
        try
        {
            FileInputStream fis = new FileInputStream(gameFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
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

        return game;
    }

    static Game getGameByName(String gameName)
    {
        File dir = new File(SerialUtils.GAMES_DIRECTORY);
        File[] directoryListing = dir.listFiles();
        Game game = null;
        if (directoryListing != null)
        {
            for (File gameFile : directoryListing)
            {
                if(gameFile.toString().contains(gameName))
                {
                    game = SerialUtils.getGameFromFile(gameFile);
                    break;
                }
            }
        }
        return game;
    }
}
