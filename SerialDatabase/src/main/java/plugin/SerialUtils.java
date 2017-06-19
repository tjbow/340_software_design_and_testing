package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Player;
import com.group4.shared.command.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialUtils
{
    protected static final String GAMES_DIRECTORY = "ttr/games/";
    protected static final String USERS_DIRECTORY = "ttr/users/";
    protected static final String COMMANDS_DIRECTORY = "ttr/commands/";
    protected static final String PLAYERS_DIRECTORY = "ttr/players/";

    static Game getGameFromFile(File gameFile)
    {
        Game game = null;
        try
        {
            if(gameFile.isDirectory())
            {
                return null;
            }
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

        List<Player> players = getPlayersForGame(game.getGameName());
        game.setPlayers(players);
        return game;
    }

    private static List<Player> getPlayersForGame(String gameName)
    {
        List<Player> players = null;

        File playersDir = new File(SerialUtils.PLAYERS_DIRECTORY);
        File[] playersDirListing = playersDir.listFiles();

        if(playersDirListing != null)
        {
            for(File playerFile : playersDirListing)
            {
                if(playerFile.getName().toString().contains(gameName))
                {
                    try
                    {

                        FileInputStream fis = new FileInputStream(playerFile);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        players = (List<Player>) ois.readObject();
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
                }
            }
        }

        return players;
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
