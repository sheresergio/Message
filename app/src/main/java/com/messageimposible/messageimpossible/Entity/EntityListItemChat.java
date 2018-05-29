package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemChat {

    private int img;
    private String name;
    private String lastMessage;
    private String lastConnection;

    public EntityListItemChat(){

        this.img = R.mipmap.message_impossible_icon;
        this.name = "";
        this.lastMessage = "";
        this.lastConnection = "";

    }

    public EntityListItemChat(int img, String name, String lastMessage, String lastConnection){

        this.img = img;
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastConnection = lastConnection;

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }
}
