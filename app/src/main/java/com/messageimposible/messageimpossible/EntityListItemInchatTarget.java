package com.messageimposible.messageimpossible;

public class EntityListItemInchatTarget {

    private int id;
    private String time;
    private String message;

    public EntityListItemInchatTarget(){

        this.id = 0;
        this.time = "";
        this.message="";

    }

    public EntityListItemInchatTarget(int id, String time, String message){

        this.id = id;
        this.time = time;
        this.message= message;

    }

    public int getImg() {
        return id;
    }

    public void setImg(int id) {
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