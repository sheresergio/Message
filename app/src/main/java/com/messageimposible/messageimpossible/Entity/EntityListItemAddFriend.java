package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemAddFriend {

    private int img;
    private String name;
    private String email;

    public EntityListItemAddFriend(){

        this.img = R.mipmap.message_impossible_icon;
        this.name = "";
        this.email = "";

    }

    public EntityListItemAddFriend(int img, String name, String email){

        this.img = img;
        this.name = name;
        this.email = email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
