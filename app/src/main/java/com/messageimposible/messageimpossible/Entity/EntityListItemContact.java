package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemContact {

    private int img;
    private String name;
    private String online;
    private String lastConnection;

    public EntityListItemContact(){

        this.img = R.mipmap.message_impossible_icon;
        this.name = "";
        this.online = "";
        this.lastConnection = "";

    }

    public EntityListItemContact(int img, String name, String online, String lastConnection){

        this.img = img;
        this.name = name;
        this.online = online;
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

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }
}
