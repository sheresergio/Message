package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemAddFriend {

    private int img;
    private String id;
    private String username;
    private String email;

    public EntityListItemAddFriend(){

        this.img = R.mipmap.message_impossible_icon;

    }

    public EntityListItemAddFriend(int img, String id, String name, String email){

        this.img = img;
        this.id = id;
        this.username = name;
        this.email = email;

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
