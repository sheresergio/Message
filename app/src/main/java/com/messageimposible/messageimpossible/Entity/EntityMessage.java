package com.messageimposible.messageimpossible.Entity;

public class EntityMessage {

    private String id;
    private String message;

    public EntityMessage(){
    }

    public EntityMessage(String id, String message){//int id

        this.id = id;
        this.message= message;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}