package com.messageimposible.messageimpossible;

public class EntityListItemInchatOwner {

    private int id;
    private String time;
    private String message;

    public EntityListItemInchatOwner(){

        this.id = 0;
        this.time = "";
        this.message="";

    }

    public EntityListItemInchatOwner(int id, String time, String message){

        this.id = id;
        this.message= message;
        this.time = time;

    }

    public int getName() {
        return id;
    }

    public void setName(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String answer) {
        this.message = message;
    }

}