package com.example;

import java.util.Arrays;
import java.util.Iterator;

public class Driver {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User user1 = new User("Alice", server);
        User user2 = new User("Bob", server);
        User user3 = new User("Charlie", server);

        server.registerUser(user1);
        server.registerUser(user2);
        server.registerUser(user3);

        user1.sendMessage(Arrays.asList("Bob", "Charlie"), "Hello Bob and Charlie"); 
        user2.sendMessage(Arrays.asList("Alice"), "Hi Alice!");
        user3.sendMessage(Arrays.asList("Alice", "Bob"), "Hey Alice and Bob!");

         
        user1.viewChatHistory();
        user2.viewChatHistory();
        user3.viewChatHistory();
        

        //System.out.println(user2.chatHistory.getSize());

        System.out.println("----------------------");
        //user1.undoLastMessageSent();

        user1.viewChatHistory();

        
        user1.viewChatHistory();
        System.out.println("----------------------");


        //user2.blockUser("Alice");

        //user1.sendMessage(Arrays.asList("Bob", "Charlie"), "This message should be blocked for Alice.");

        user1.viewChatHistory();

        System.out.println("\nMessages sent or received by user1:"); 
        Iterator<Message> user2Iterator = user2.iterator();
        while(user2Iterator.hasNext()) {
            Message message = user2Iterator.next();
            if(message != null) {
                System.out.println(message.getSender() + " to " + message.getRecipient() + ": " + message.getMessageContent());
            }
        }

    
    
    
}
}