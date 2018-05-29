package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemAddFriend {

    private int img;
    private String username;
    private String email;

    public EntityListItemAddFriend(){

        this.img = R.mipmap.message_impossible_icon;
        this.username = "";
        this.email = "";

    }

    public EntityListItemAddFriend(int img, String name, String email){

        this.img = img;
        this.username = name;
        this.email = email;

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
