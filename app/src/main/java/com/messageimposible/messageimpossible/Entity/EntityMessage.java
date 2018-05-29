package com.messageimposible.messageimpossible.Entity;

public class EntityMessage {

    //private int id;
    private String username;
    private String message;

    public EntityMessage(){
    }

    public EntityMessage(String name, String message){//int id

        //this.id = id;
        this.message= message;
        this.username = name;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}