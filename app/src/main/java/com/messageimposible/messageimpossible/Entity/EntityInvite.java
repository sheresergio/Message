package com.messageimposible.messageimpossible.Entity;

public class EntityInvite {

    private String username;
    private String email;

    public EntityInvite(){

    }

    public EntityInvite(String name, String email){//int id

        this.username = name;
        this.email = email;

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