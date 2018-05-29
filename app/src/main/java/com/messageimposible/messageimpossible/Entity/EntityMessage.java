package com.messageimposible.messageimpossible.Entity;

public class EntityMessage {

    //private int id;
    private String name;
    private String message;

    public EntityMessage(){
    }

    public EntityMessage(String name, String message){//int id

        //this.id = id;
        this.message= message;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}