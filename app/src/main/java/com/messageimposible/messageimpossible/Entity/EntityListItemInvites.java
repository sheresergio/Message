package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

public class EntityListItemInvites {

    private int img;
    private String name;
    private String inviteMessage;

    public EntityListItemInvites(){

        this.img = R.mipmap.message_impossible_icon;
        this.name = "";
        this.inviteMessage = "";

    }

    public EntityListItemInvites(int img, String name, String inviteMessage, String date){

        this.img = img;
        this.name = name;
        this.inviteMessage = inviteMessage;

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

    public String getInviteMessage() {
        return inviteMessage;
    }

    public void setInviteMessage(String inviteMessage) {
        this.inviteMessage = inviteMessage;
    }

}
