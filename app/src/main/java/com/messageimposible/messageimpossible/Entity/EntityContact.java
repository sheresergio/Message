package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityContact {

    private String id;
    private int img;
    private String username;

    public EntityContact() {

        this.img = R.mipmap.message_impossible_icon;

    }

    public EntityContact(String id, String username, String email) {
        this.img = R.mipmap.message_impossible_icon;
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
