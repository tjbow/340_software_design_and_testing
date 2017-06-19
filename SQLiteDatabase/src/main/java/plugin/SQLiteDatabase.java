package plugin;

import com.group4.shared.Model.Game.Game;
import com.group4.shared.Model.Game.GameList;
import com.group4.shared.Model.User;
import com.group4.shared.command.ClientCommand;
import com.group4.shared.command.Command;
import com.group4.shared.plugin.IPersistencePlugin;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class SQLiteDatabase implements IPersistencePlugin {


    public SQLiteDatabase()
    {
        create();
    }

    //------INTERFACE IMPLEMENTATION------------------

    @Override
    public void saveGame(Game game) {
        deleteGame(game);
        SQLiteTransaction transaction = new SQLiteTransaction();
        SQLiteGameDao gameDao = new SQLiteGameDao(transaction);
        gameDao.saveGame(game);

        transaction.closeConnection();
    }

    @Override
    public void updateCommands(String gameName, List<Command> commands) {
        SQLiteTransaction transaction = new SQLiteTransaction();
        SQLiteCommandDao commandDao = new SQLiteCommandDao(transaction);
        commandDao.deleteCommandList(gameName);
        commandDao.updateCommands(gameName, commands);

        transaction.closeConnection();
    }

    @Override
    public void saveUsers(List<User> users) {
        SQLiteTransaction transaction = new SQLiteTransaction();
        SQLiteUserDao userDao = new SQLiteUserDao(transaction);
        userDao.clear();
        userDao.saveUsers(users);

        transaction.closeConnection();
    }

    @Override
    public GameList getGames() {
        SQLiteTransaction transaction = new SQLiteTransaction();
        SQLiteGameDao gameDao = new SQLiteGameDao(transaction);
        GameList gameList = gameDao.getGames();
        transaction.closeConnection();
        //now add additional commands to each game
//        for (Game game : gameList.getGameList())
//        {
//            List<ClientCommand> commands = getCommands(game.getGameName());
//            game.getCommandList().setCommandList(commands);
////            for (ClientCommand command : commands)
////            {
////                game.getCommandList().add(command);
////            }
//        }

        return gameList;
    }

    @Override
    public List<User> getUsers() {
        SQLiteTransaction transaction = new SQLiteTransaction();
        List<User> users = new SQLiteUserDao(transaction).getUsers();

        transaction.closeConnection();
        return users;
    }

    @Override
    public List<Command> getCommands(String gameName) {
        SQLiteTransaction transaction = new SQLiteTransaction();
        SQLiteCommandDao commandDao = new SQLiteCommandDao(transaction);
        List<Command> commands = commandDao.getCommands(gameName);

        transaction.closeConnection();
        return commands;
    }

    @Override
    public void clear() {
        SQLiteTransaction transaction = new SQLiteTransaction();
        new SQLiteGameDao(transaction).clear();
        new SQLiteCommandDao(transaction).clear();
        new SQLiteUserDao(transaction).clear();
        transaction.closeConnection();
    }

    @Override
    public void deleteGame(Game game) {
        SQLiteTransaction transaction = new SQLiteTransaction();
        new SQLiteGameDao(transaction).deleteGame(game);
        new SQLiteCommandDao(transaction).deleteCommandList(game.getGameName());

        transaction.closeConnection();
    }

    private void create() {
        //call create method on each of the individual DAO's
        SQLiteTransaction transaction = new SQLiteTransaction();
        new SQLiteGameDao(transaction).create();
        new SQLiteCommandDao(transaction).create();
        new SQLiteUserDao(transaction).create();
        //end transaction
        transaction.closeConnection();
    }
}
