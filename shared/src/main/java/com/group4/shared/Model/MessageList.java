package com.group4.shared.Model;

import java.util.List;

/**
 * Created by Russell Fitzpatrick on 5/24/2017.
 */

public class MessageList {

    private List<Message> messageList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void add(Message message){
        messageList.add(message);
    }
}
