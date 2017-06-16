package com.group4.shared.plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.Model.User;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;

import java.util.List;

/**
 * Created by beefhead on 6/14/2017.
 */

public interface IPersistencePlugin {

    public void saveGame(Game game);
    public void updateCommands(String gameName, List<ClientCommand> commands);
    public void saveUsers(List<User> users);
    public GameList getGames();
    public List<User> getUsers();
    public List<ClientCommand> getCommands(String gameName);
    public void clear();
    public void deleteGame(Game game);

}
