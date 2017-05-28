package com.group4.server.ServerModel;

import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.DestinationCard;
import com.group4.shared.Model.GAME_STATUS;
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
import com.group4.shared.command.Client.CUpdateGameStatsCommandData;
import com.group4.shared.command.Client.CUpdatePlayersCommandData;

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
        boolean success = serverModel.createGame(gameName, numberOfPlayers);
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
        joinGameCommandData.setGameName(gameName);

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

        Game game = serverModel.getGameList().getGameByName(gameName);

        //CREATE A PLAYERDATA COMMAND TO ADD TO THE GAME BEING PLAYED
        CUpdatePlayersCommandData updatePlayersCommandData = new CUpdatePlayersCommandData();
        updatePlayersCommandData.setType("updateplayers");
        updatePlayersCommandData.setPlayerData(game.getPlayers());

        //CREATE AN UPDATEGAME COMMAND TO SEND
        CUpdateGameCommandData updateGameCommandData = new CUpdateGameCommandData();
        updateGameCommandData.setType("updategame");
        updateGameCommandData.setStatus(game.getStatus());

        //ADD THE ABOVE COMMANDS TO THE GAME SO EVERYONE CAN RECEIVE IT
        game.addCommand(updatePlayersCommandData);
        updatePlayersCommandData.setCommandNumber(game.getNewCommandIndex());
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
        Game game = serverModel.getGameList().getGameByName(gameName);

        //CREATE A PLAYERDATA COMMAND TO ADD TO THE GAME BEING PLAYED
        CUpdatePlayersCommandData updatePlayersCommandData = new CUpdatePlayersCommandData();
        updatePlayersCommandData.setType("updateplayers");
        updatePlayersCommandData.setPlayerData(game.getPlayers());

        //CREATE A STARTGAME COMMAND TO ADD TO THE GAME BEING PLAYED
        CStartGameCommandData startGameCommandData = new CStartGameCommandData();
        startGameCommandData.setType("startgame");
        startGameCommandData.setWasSuccessful(true);

        //CREATE A GAMESTATS COMMAND TO ADD TO THE GAME BEING PLAYED
        CUpdateGameStatsCommandData updateStatsCommandData = new CUpdateGameStatsCommandData();
        updateStatsCommandData.setType("updatestats");
        updateStatsCommandData.setGameStats(game.getGameStats());

        //CREATE AN UPDATEGAME COMMAND TO ADD TO THE GAME BEING PLAYED
        CUpdateGameCommandData updateGameCommandData = new CUpdateGameCommandData();
        updateGameCommandData.setType("updategame");
        updateGameCommandData.setStatus(game.getStatus());

        //ADD THE ABOVE TO THE GAME FOR RETRIEVAL BY ALL PLAYERS
        game.addCommand(updatePlayersCommandData);
        updatePlayersCommandData.setCommandNumber(game.getNewCommandIndex());

        game.addCommand(startGameCommandData);
        startGameCommandData.setCommandNumber(game.getNewCommandIndex());

        game.addCommand(updateStatsCommandData);
        updateStatsCommandData.setCommandNumber(game.getNewCommandIndex());

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

        //System.out.println("getGameList() was called.");
        return new Results(true, null, null, cmdList);
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
        endGameCommandData.setWasSuccessful(true);
        game.addCommand(endGameCommandData);
        endGameCommandData.setCommandNumber(game.getNewCommandIndex());

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
    public Results drawDestinationCards(String userName)
    {
        Game game = serverModel.getGameList().getGameByUsername(userName);

//        get the player and add selectedCards to his destination card deck
        game.playerTurn_DrawDestinationCards(userName);

        CUpdatePlayersCommandData updatePlayersCommandData = new CUpdatePlayersCommandData();
        updatePlayersCommandData.setType("updateplayers");
        updatePlayersCommandData.setPlayerData(game.getPlayers());

        CUpdateGameCommandData updateGameCommandData = new CUpdateGameCommandData();
        updateGameCommandData.setType("updategame");
        updateGameCommandData.setStatus(GAME_STATUS.ONGOING);
        updateGameCommandData.setDeckState(game.getDecks());

        game.addCommand(updatePlayersCommandData);
        updatePlayersCommandData.setCommandNumber(game.getNewCommandIndex());
        game.addCommand(updateGameCommandData);
        updateGameCommandData.setCommandNumber(game.getNewCommandIndex());

        System.out.println("Destination Cards Drawn");

        return new Results(true, "Destination cards drawn", null, null);
    }

    @Override
    public Results returnDestinationCard(List<DestinationCard> returnedCard)
    {
//        take the card within returnedCard(s) and insert it into the game dest card deck
        Game game = serverModel.getGameList().getGameByUsername(serverModel.getTempUser().getUsername());
        String userName = serverModel.getTempUser().getUsername();
        game.playerTurn_ReturnDestinationCards(userName, returnedCard);

        CUpdateGameCommandData updateGameCommandData = new CUpdateGameCommandData();
        updateGameCommandData.setType("updategame");
        updateGameCommandData.setStatus(GAME_STATUS.ONGOING);
        updateGameCommandData.setDeckState(game.getDecks());

        CUpdatePlayersCommandData updatePlayersCommandData = new CUpdatePlayersCommandData();
        updatePlayersCommandData.setType("updateplayers");
        updatePlayersCommandData.setPlayerData(game.getPlayers());

        game.addCommand(updateGameCommandData);
        updateGameCommandData.setCommandNumber(game.getNewCommandIndex());
        game.addCommand(updatePlayersCommandData);
        updatePlayersCommandData.setCommandNumber(game.getNewCommandIndex());

        int cardsSelected = 3 - returnedCard.size();
        System.out.println("Player " + serverModel.getTempUser().getUsername() +
                " selected " + cardsSelected + " destination cards.");

        return new Results(true, "Destination cards selected", null, null);
    }
}
