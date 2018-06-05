package com.messageimposible.messageimpossible.Entity;

import java.util.Map;

public class EntityMessageOwner extends EntityMessage{

    private Map time;

    public EntityMessageOwner() {
    }

    public EntityMessageOwner(Map time) {
        this.time = time;
    }

    public EntityMessageOwner(String id, String message, Map time) {
        super(id, message);
        this.time = time;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}