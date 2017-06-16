package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.plugin.IGameDao;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialGameDao implements IGameDao
{
    @Override
    public void saveGame(Game game)
    {
        try
        {
            // write object to file
            FileOutputStream fos = new FileOutputStream(SerialUtils.GAMES_DIRECTORY + game.getGameName() + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.close();

        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GameList getGames()
    {
        GameList gameList = new GameList(new ArrayList<Game>());

        File dir = new File(SerialUtils.GAMES_DIRECTORY);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null)
        {
            for (File gameFile : directoryListing)
            {
                gameList.add(SerialUtils.getGameFromFile(gameFile));
            }
        }
        else
        {
            return null;
        }

        return gameList;
    }

    @Override
    public void deleteGame(Game game)
    {
        File dir = new File(SerialUtils.GAMES_DIRECTORY);
        File[] directoryListing = dir.listFiles();

        for (File gameFile : directoryListing)
        {
            if(gameFile.getName().contains(game.getGameName()))
            {
                gameFile.delete();
                break;
            }
        }
    }

    @Override
    public void clear()
    {
        try
        {
            FileUtils.cleanDirectory(new File(SerialUtils.GAMES_DIRECTORY));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
