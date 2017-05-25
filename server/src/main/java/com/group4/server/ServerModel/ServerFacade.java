package com.group4.server.ServerModel;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.Game;
import com.group4.shared.Model.Message;
import com.group4.shared.Model.Player;
import com.group4.shared.Proxy.IServer;
import com.group4.shared.Model.Results;
import com.group4.shared.Model.User;
import com.group4.shared.command.Client.CEndGameCommandData;
import com.group4.shared.command.Client.CGetGameListCommandData;
import com.group4.shared.command.Client.CJoinGameCommandData;
import com.group4.shared.command.Client.CLoginCommandData;
import com.group4.shared.command.Client.CRegisterCommandData;
import com.group4.shared.command.Client.CStartGameCommandData;
import com.group4.shared.command.Client.CUpdateChatCommandData;
import com.group4.shared.command.Client.CUpdateGameCommandData;

import java.util.List;

/*
 * Created by Tom on 5/14/2017.
 */

/**
 * Provides access to important server functions
 */
public class ServerFacade implements IServer
{
    ServerModel serverModel = ServerModel.getInstance();

    @Override
    public Results login(User user)
    {
        String authToken = serverModel.loginUser(user);
        if(authToken != null)
        {
            //CREATE A GAMELIST COMMAND TO SEND
            CGetGameListCommandData getGameListCommandData = new CGetGameListCommandData();
            getGameListCommandData.setType("getgamelist");
            getGameListCommandData.setGameList(serverModel.getGameList());

            //CREATE A LOGIN COMMAND TO SEND
            CLoginCommandData loginData = new CLoginCommandData();
            loginData.setAuthToken(authToken);
            loginData.setUsername(user.getUsername());
            loginData.setType("login");

            //ADD THE ABOVE COMMANDS TO THE COMMANDLIST FOR THE RESULTS
            CommandList cmdList = new CommandList();
            cmdList.add(getGameListCommandData);
            cmdList.add(loginData);

            System.out.println("User " + user.getUsername() + " logged in");

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

            //CREATE A GAMELIST COMMAND TO SEND
            CGetGameListCommandData getGameListCommandData = new CGetGameListCommandData();
            getGameListCommandData.setType("getgamelist");
            getGameListCommandData.setGameList(serverModel.getGameList());

            //CREATE A REGISTER COMMAND TO SEND
            CRegisterCommandData registerData = new CRegisterCommandData();
            registerData.setAuthToken(authToken);
            registerData.setType("register");
            registerData.setUsername(user.getUsername());

            //ADD THE ABOVE COMMANDS TO THE COMMANDLIST FOR THE RESULTS
            CommandList cmdList = new CommandList();
            cmdList.add(getGameListCommandData);
            cmdList.add(registerData);

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
        boolean success = serverModel.createGame(game);
        if(!success)
        {
            return new Results(false, null, "A game with that name already exists.", null);
        }

        //CREATE A GAMELIST COMMAND TO SEND (necessary for the client to immediately join the game upon receipt)
        CGetGameListCommandData gameListCommandData = new CGetGameListCommandData();
        gameListCommandData.setType("getgamelist");
        gameListCommandData.setGameList(serverModel.getGameList());

        //CREATE A JOINGAME COMMAND TO SEND
        CJoinGameCommandData joinGameCommandData = new CJoinGameCommandData();
        joinGameCommandData.setType("joingame");
        joinGameCommandData.setGameName(game.getGameName());

        //ADD THE ABOVE COMMANDS TO THE COMMANDLIST FOR THE RESULTS
        CommandList cmdList = new CommandList();
        cmdList.add(gameListCommandData);
        cmdList.add(joinGameCommandData);

        return new Results(true, null, null, cmdList);
    }

    @Override
    public Results joinGame(String gameName)
    {
        Player player = new Player(serverModel.getTempUser());

        boolean success = serverModel.joinGame(gameName, player);
        if(!success)
        {
            return new Results(false, null, "Sorry, this game is full", null);
        }

        //CREATE AN UPDATEGAME COMMAND TO SEND
        Game game = serverModel.getGameList().getGameByName(gameName);
        CUpdateGameCommandData updateGameCommandData = new CUpdateGameCommandData();
        updateGameCommandData.setType("updategame");
        updateGameCommandData.setStatus(game.getStatus());
        updateGameCommandData.setPlayerList(game.getPlayers());
        //ADD THE UPDATEGAME COMMAND TO THE GAME SO EVERYONE CAN RECEIVE IT
        game.addCommand(updateGameCommandData);
        updateGameCommandData.setCommandNumber(game.getNewCommandIndex());

        //CREATE A JOINGAME COMMAND TO SEND
        CJoinGameCommandData joinGameCommandData = new CJoinGameCommandData();
        joinGameCommandData.setType("joingame");
        joinGameCommandData.setGameName(gameName);

        //ADD THE ABOVE COMMANDS TO THE COMMANDLIST TO PUT IN RESULTS OBJECT
        CommandList cmdList = new CommandList();
        cmdList.add(joinGameCommandData);

        return new Results(true, null, null, cmdList);
    }

    @Override
    public Results startGame(String gameName)
    {
        serverModel.startGame(gameName);

        //CREATE A STARTGAME COMMAND TO ADD TO THE GAME BEING PLAYED
        CStartGameCommandData startGameCommandData = new CStartGameCommandData();
        startGameCommandData.setType("startgame");
        startGameCommandData.setWasSuccessful(true);

        //CREATE AN UPDATEGAME COMMAND TO ADD TO THE GAME BEING PLAYED
        Game game = serverModel.getGameList().getGameByName(gameName);
        CUpdateGameCommandData updateGameCommandData = new CUpdateGameCommandData();
        updateGameCommandData.setType("updategame");
        updateGameCommandData.setStatus(game.getStatus());
        updateGameCommandData.setPlayerList(game.getPlayers());

        //ADD STARTGAME AND UPDATEGAME TO THE GAME FOR RETRIEVAL BY ALL PLAYERS
        game.addCommand(startGameCommandData);
        startGameCommandData.setCommandNumber(game.getNewCommandIndex());
        game.addCommand(updateGameCommandData);
        updateGameCommandData.setCommandNumber(game.getNewCommandIndex());

        return new Results(true, null, null, null);
    }

    @Override
    public Results getGameList()
    {
        //CREATE A GAMELIST COMMAND TO SEND
        CGetGameListCommandData gameListCommandData = new CGetGameListCommandData();
        gameListCommandData.setType("getgamelist");
        gameListCommandData.setGameList(serverModel.getGameList());

        //PUT THE ABOVE IN A COMMANDLIST TO PUT IN THE RESULTS OBJECT
        CommandList cmdList = new CommandList();
        cmdList.add(gameListCommandData);

        Results results = new Results(true, null, null, cmdList);
        //System.out.println("getGameList() was called.");
        return results;
    }

    @Override
    public Results endGame(String gameName)
    {
        boolean success = serverModel.endGame(gameName);
        if(!success)
        {
            return new Results(true, null, "Cannot end game - game does not exist", null);
        }

        Game game = serverModel.getGameList().getGameByName(gameName);
        CEndGameCommandData endGameCommandData = new CEndGameCommandData();
        endGameCommandData.setType("endgame");
        endGameCommandData.setWasSuccessful(success);
        game.addCommand(endGameCommandData);
        endGameCommandData.setCommandNumber(game.getNewCommandIndex());

//        CommandList cmdList = new CommandList();
//        cmdList.add(endGameCommandData);

        System.out.println("The game " + gameName + " was ended.");
        return new Results(true, null, null, null);
    }

    @Override
    public Results sendChat(Message message)
    {
        Game game = serverModel.getGameList().getGameByUsername(message.getUserName());

        if(game == null) return new Results(false, null, "Chat cannot be sent - Game does not exist", null);

        game.addMessage(message);

        //CREATE AN UPDATECHAT COMMAND TO SEND
        CUpdateChatCommandData updateChatCommandData = new CUpdateChatCommandData();
        updateChatCommandData.setType("updatechat");
        updateChatCommandData.setChatHistory(game.getChatHistory());

        //ADD THE UPDATECHAT COMMAND SO EVERYONE CAN RECEIVE IT
        game.addCommand(updateChatCommandData);
        updateChatCommandData.setCommandNumber(game.getNewCommandIndex());

        //Maybe remove this?
        //ADD THE UPDATECHAT TO SEND BACK WITH THE RESULTS OBJECT
//        CommandList cmdList = new CommandList();
//        cmdList.add(updateChatCommandData);

        System.out.println("Player " + message.getUserName() +
                " in game " + game.getGameName() +
                " chatted the message \"" + message.getMessage() + "\"");

        return new Results(true, "Chat Sent", null, null);
    }

    @Override
    public Results getPendingCommands(User user, int lastCmdExecuted)
    {
        //get the game the user is in
        Game game = serverModel.getGameList().getGameByUsername(user.getUsername());

        //get the commandlist based on the index passed
        CommandList playerCommandList = game.getCommandList().getCommandListAfterIndex(lastCmdExecuted);

        return new Results(true, "Pending Commands", null, playerCommandList);
    }

    @Override
    public Results drawDestinationCards(String userName, List<DestinationCard> selectedCards)
    {
        Game game = serverModel.getGameList().getGameByUsername(userName);

//        get the player and add selectedCards to his destination card deck

//        CUpdatePlayersCommandData updatePlayersCommandData = new CUpdatePlayersCommandData();
//        updatePlayersCommandData.setType("updateplayers");
//        updatePlayersCommandData.setPlayerData();

//        game.addCommand(updatePlayersCommandData);

//        CommandList cmdList = new CommandList();
//        cmdList.add(updatePlayersCommandData);

        System.out.println("Player " + userName +
                " selected " + selectedCards.size() + " destination cards.");

        return new Results(false, null, "not yet implemented", null);
    }

    @Override
    public Results returnDestinationCard(List<DestinationCard> returnedCard)
    {
//        take the card within returnedCard and insert it into the game dest card deck

        return new Results(false, null, "returnDestinationCard not yet implemented on server", null);
    }
}
