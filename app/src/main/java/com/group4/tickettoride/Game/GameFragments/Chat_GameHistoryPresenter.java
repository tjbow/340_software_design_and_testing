package com.group4.tickettoride.Game.GameFragments;


import com.group4.shared.Model.ChatHistory;
import com.group4.shared.Model.MessageList;
import com.group4.shared.Model.TurnHistory;
import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.NextLayerFacade.NextLayerFacade;

import java.util.Observable;
import java.util.Observer;

public class Chat_GameHistoryPresenter implements Observer{

    private Chat_GameHistoryFragment fragment;
    private MessageList chatMessages;
    private MessageList gameHistory;

    public Chat_GameHistoryPresenter(Chat_GameHistoryFragment fragment)
    {
        this.fragment = fragment;
        ClientModel.SINGLETON.addObserver(this);
        //initialize messageLists
        chatMessages = ClientModel.SINGLETON.getChatHistory();
        gameHistory = ClientModel.SINGLETON.getTurnHistory();

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == ChatHistory.class)
        {
            this.chatMessages = (ChatHistory) arg;
            updateMessages();
        }
        else if (arg.getClass() == TurnHistory.class)
        {
            this.gameHistory = (TurnHistory) arg;
            updateMessages();
        }
    }

    public void chatSelected()
    {
        fragment.setMessageList(chatMessages.getMessageList());
    }

    public void gameHistorySelected()
    {
        fragment.setMessageList(gameHistory.getMessageList());
    }

    public void sendButtonClicked()
    {
        //get text
        String message = fragment.getMessageText();
        //clear text
        fragment.clearMessageText();
        //call next layer facade sendmessage
        NextLayerFacade.SINGLETON.sendMessage(message);
    }

    private void updateMessages()
    {
        if (fragment.getMessageListType().equals("chat"))
        {
            fragment.setMessageList(chatMessages.getMessageList());
        }
        else if (fragment.getMessageListType().equals("gameHistory"))
        {
            fragment.setMessageList(gameHistory.getMessageList());
        }
    }
}
