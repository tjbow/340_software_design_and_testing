package plugin;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.shared.plugin.ICommandDao;

import java.io.File;
import java.util.List;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialCommandDao implements ICommandDao
{

    @Override
    public void updateCommands(String gameName, List<ClientCommand> commands)
    {
        Game game = getGameByName(gameName);

        if(game != null)
        {
            CommandList cmdList = new CommandList();
            cmdList.setCommandList(commands);
            game.setCommandList(cmdList);

            new SerialGameDao().saveGame(game);
        }
    }

    @Override
    public List<ClientCommand> getCommands(String gameName)
    {
        Game game = getGameByName(gameName);
        CommandList cmdList = null;

        if(game != null)
        {
            cmdList = game.getCommandList();
        }

        return cmdList.getCommandList();
    }

    private Game getGameByName(String gameName)
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

    @Override
    public void clear()
    {

    }

    @Override
    public void create()
    {

    }
}
