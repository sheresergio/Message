package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemChat {

    private int img;
    private String id;
    private String username;
    private String lastMessage;
    private String lastConnection;

    public EntityListItemChat(){

        this.img = R.mipmap.message_impossible_icon;
        this.lastConnection = "just now";

    }

    public EntityListItemChat(int img, String id, String name, String lastMessage, String lastConnection){

        this.img = img;
        this.id = id;
        this.username = name;
        this.lastMessage = lastMessage;
        this.lastConnection = lastConnection;

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
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
