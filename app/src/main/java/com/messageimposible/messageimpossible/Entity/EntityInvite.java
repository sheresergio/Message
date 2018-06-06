package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityInvite {

    private String id;
    private int img;
    private String username;
    private String email;

    public EntityInvite(){

        this.img = R.mipmap.message_impossible_icon;

    }

    public EntityInvite(String id, String name, String email){

        this.id = id;
        this.username = name;
        this.email = email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}