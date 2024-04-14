package com.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SearchMessageByUser implements Iterator<Message>{
    private User user; 
    private Iterator<Message> iterator; 

    public SearchMessageByUser(User user, Iterator<Message> iterator) {
        this.user = user;
        this.iterator = iterator; 
    }

    @Override
    public boolean hasNext() {
        while(iterator.hasNext()) {
            Message message = iterator.next();
            if(message.getSender().equals(user.getUserName()) || message.getRecipient().contains(user.getUserName())){
                return true; 
            }
        }
        return false; 
    }

    @Override
    public Message next() {
        while(iterator.hasNext()) {
            Message message = iterator.next();
            if(message.getSender().equals(user.getUserName()) || message.getRecipient().contains(user.getUserName())){
                return message;
            }
        }
        return null; 
    }
    
}
