package com.group4.server.ServerModel;

import com.group4.server.Command.LoginCommand;
import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.GameList;
import com.group4.shared.Model.Player;
import com.group4.shared.Proxy.IClient;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CCreateGameCommandData;
import com.group4.shared.command.Client.CGetGameListCommandData;
import com.group4.shared.command.Client.CJoinGameCommandData;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.Client.CRegisterCommandData;
import com.group4.shared.command.Server.LoginCommandData;

import java.util.List;

/*
 * Created by Tom on 5/14/2017.
 */

/**
 * Provides access to important server functions
 */
public class ServerFacade implements IServer, IClient
{
    ServerModel serverModel = ServerModel.getInstance();

    @Override
    public Results login(User user)
    {
        String authToken = serverModel.loginUser(user);
        if(authToken != null)
        {
            System.out.println("User " + user.getUsername() + " logged in");

            CLoginCommandData loginData = new CLoginCommandData();
            loginData.setAuthToken(authToken);
            loginData.setType("login");

            CommandList cmdList = new CommandList();
            cmdList.commandList.add(loginData);

            return new Results(true, authToken, null, cmdList);
        }
        else
        {
            System.out.println("User " + user.getUsername() + " attempted to login");
            return new Results(false, null, "Invalid login credentials", null);
        }
    }

    @Override
    public Results register(User user)
    {
        String authToken = serverModel.registerUser(user);
        if(authToken != null)
        {
            System.out.println("User " + user.getUsername() + " registered");

            CRegisterCommandData registerData = new CRegisterCommandData();
            registerData.setAuthToken(authToken);
            registerData.setType("register");

            CommandList cmdList = new CommandList();
            cmdList.commandList.add(registerData);

            return new Results(true, authToken, null, cmdList);
        }
        else
        {
            System.out.println("User " + user.getUsername() + " failed to register");
            return new Results(false, null, "That username is taken", null);
        }
    }

    @Override
    public Results createGame(String gameName, int numberOfPlayers)
    {
        Game game = new Game(gameName, numberOfPlayers);
        serverModel.addGame(game);

        System.out.println("Game \"" + game.getGameName() + "\" created with " + game.getPlayerCount() + " players.");

        CGetGameListCommandData gameListCommandData = new CGetGameListCommandData();
        gameListCommandData.setType("getgamelist");
        gameListCommandData.setGameList(serverModel.getGameList());

        CommandList cmdList = new CommandList();
        cmdList.commandList.add(gameListCommandData);

        return new Results(true, null, null, cmdList);
    }

    @Override
    public Results joinGame(int gameId)
    {
//        if(true)
//        {
//            return new Results(false, null, "joinGame not yet functional", null);
//        }

        GameList gameList = serverModel.getGameList();
        Game game = gameList.getGameList().get(gameId);

        if(game.getCurrentPlayerSize() > game.getPlayerCount())
        {
            //don't join
            return new Results(false, null, "Sorry, this game is full", null);
        }

        Player player = new Player(ServerModel.getInstance().getTempUser());

        game.addPlayer(player.getUserName(), player);

        System.out.println("Player " + player.getUserName() + " added to game \""
                + game.getGameName());

//        CJoinGameCommandData joinGameCommandData = new CJoinGameCommandData();
//        joinGameCommandData.setType("joingame");
//        joinGameCommandData.

        CGetGameListCommandData gameListCommandData = new CGetGameListCommandData();
        gameListCommandData.setType("getgamelist");
        gameListCommandData.setGameList(serverModel.getGameList());

        CommandList cmdList = new CommandList();
        cmdList.commandList.add(gameListCommandData);

        return new Results(true, null, null, cmdList);
    }

    @Override
    public Results startGame(int gameId)
    {
        return null;
    }

    @Override
    public Results reportGameState()
    {
        //TODO: Tom: what is this method supposed to do?
        return null;
    }

    @Override
    public Results getGameList()
    {
        //return serverModel.getGameList();
        return null;
    }
}
