package com.example;

import java.util.Iterator;
import java.util.List;
import java.util.*;

public class User implements Iterable<Message> {
    private String username;
    public ChatHistory chatHistory; 
    //public ChatHistory receivedHistory; 
    private MessageMemento lastMessageMemento; 
    private ChatServer server; 

    public User(String username, ChatServer server) {
        this.username = username; 
        this.server = server; 
        this.chatHistory = new ChatHistory(); 
    }

    public void saveLastMessageMemento(Message message) {
        lastMessageMemento = new MessageMemento(message.getMessageContent(), message.getTimestamp());
    }

    public MessageMemento getLastMessageMemento() {
        return lastMessageMemento;
    }

    public void restoreLastMessageFromMemento(MessageMemento memento) {
        if(memento != null) {
            Message restoredMessage = new Message(username, null, memento.getTimestamp(), memento.getContent()); 
            // receiveMessage(restoredMessage);
        }
    }

    public void receiveMessage(Message message) {
        System.out.println(username + " received message from " + message.getSender() + ": " + message.getMessageContent());
        chatHistory.addMessage(message);
    }

    public void sendMessage(List<String> recipient, String content) {
        Message message = new Message(username, recipient, new Date(), content);
        server.sendMessage(message);
        chatHistory.addMessage(message);
        saveLastMessageMemento(message);
        restoreLastMessageFromMemento(lastMessageMemento);
    }

    
    /*
    public void undoLastMessageSent() {
        if(lastMessageMemento != null) {
            System.out.println(username + " undid the last message.");
            Message message = new Message(username, null, null, lastMessageMemento.getContent());
            chatHistory.removeLastMessage(username);
            server.sendMessage(message);
        }
        else {
            System.out.println("No message to undo."); 
        }
    }
    */

    public void undoLastMessageSent() {
        if(lastMessageMemento != null) {
            System.out.println(username + "undid the last message sent.");
            // restoreLastMessageFromMemento(lastMessageMemento);
            chatHistory.removeLastMessage(username);
        }
        else {
            System.err.println("No message to undo.");
        }
    }

    

    /*
    public void undoLastMessageSent() {
        Message lastMessage = chatHistory.getLastMessage(); 
        System.out.println("Last message sender: " + lastMessage.getSender());
        System.out.println("Current user: " + username); 
        System.out.println(chatHistory.getSize());
        if(lastMessage != null) {
            System.out.println("asdfjklsadjfhiklsad" + lastMessage.getMessageContent());
        }

        if (username.equals(lastMessage.getSender())) {
            System.out.println("Poopie");
        }
        /*
        if(lastMessage != null && lastMessage.getSender().equals(this.username)) {
            System.out.println(username + " undid the last message sent.");
            chatHistory.removeLastMessage();
        }
        else {
            System.out.println("No message to undo.");
        }
        */

    
    

    public void blockUser(String userToBlock) {
        server.blockUser(username, userToBlock);
        System.out.println(username + " blocked messges from " + userToBlock);
    }

    public String getUserName() {
        return username; 
    }

    public void viewChatHistory() {
        System.out.println("Chat history for " + username + ":"); 
        chatHistory.printChat();
    }

    public ChatHistory getChatHistory() {
        return chatHistory; 
    }
    
    @Override
    public Iterator<Message> iterator() {
        return chatHistory.iterator(this); 
    }
}
