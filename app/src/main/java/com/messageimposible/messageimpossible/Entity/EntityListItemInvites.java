package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemInvites {

    private int img;
    private String username;
    private String inviteMessage;

    public EntityListItemInvites(){

        this.img = R.mipmap.message_impossible_icon;
        this.username = "";
        this.inviteMessage = "";

    }

    public EntityListItemInvites(int img, String name, String inviteMessage, String date){

        this.img = img;
        this.username = name;
        this.inviteMessage = inviteMessage;

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

    public String getInviteMessage() {
        return inviteMessage;
    }

    public void setInviteMessage(String inviteMessage) {
        this.inviteMessage = inviteMessage;
    }

}
