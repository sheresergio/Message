package com.messageimposible.messageimpossible;

import java.util.List;

public class EntityUsers {

    private String username;
    private String email;
    private List<EntityContact> conatcts;

    public EntityUsers() {

    }

    public EntityUsers(String username, String email, List<EntityContact> conatcts) {
        this.username = username;
        this.email = email;
        this.conatcts = conatcts;
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

    public List<EntityContact> getConatcts() {
        return conatcts;
    }

    public void setConatcts(List<EntityContact> conatcts) {
        this.conatcts = conatcts;
    }
}
