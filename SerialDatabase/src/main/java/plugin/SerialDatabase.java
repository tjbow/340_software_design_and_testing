package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.Model.User;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.shared.plugin.IPersistencePlugin;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tyler on 6/15/17.
 */

public class SerialDatabase implements IPersistencePlugin
{
    public SerialDatabase()
    {
        create();
    }

    @Override
    public void saveGame(Game game)
    {
        new SerialGameDao().saveGame(game);
    }

    @Override
    public GameList getGames()
    {
        return new SerialGameDao().getGames();
    }

    @Override
    public void deleteGame(Game game)
    {
        new SerialGameDao().deleteGame(game);
    }

    @Override
    public void updateCommands(String gameName, List<Command> commands)
    {
        new SerialCommandDao().updateCommands(gameName, commands);
    }

    @Override
    public List<Command> getCommands(String gameName)
    {
        return new SerialCommandDao().getCommands(gameName);
    }

    @Override
    public void saveUsers(List<User> users)
    {
        new SerialUserDao().saveUsers(users);
    }

    @Override
    public List<User> getUsers()
    {
        return new SerialUserDao().getUsers();
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

    private void create()
    {
        File gamesDir = new File(SerialUtils.GAMES_DIRECTORY);
        if (!gamesDir.exists())
        {
            gamesDir.mkdirs();
        }
        File usersDir = new File(SerialUtils.USERS_DIRECTORY);
        if(!usersDir.exists())
        {
            usersDir.mkdirs();
        }
        File commandsDir = new File(SerialUtils.COMMANDS_DIRECTORY);
        if(!commandsDir.exists())
        {
            commandsDir.mkdirs();
        }
        File playersDir = new File(SerialUtils.PLAYERS_DIRECTORY);
        if(!playersDir.exists())
        {
            playersDir.mkdirs();
        }
    }
}
