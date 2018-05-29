package com.messageimposible.messageimpossible.Entity;

public class EntityMessageTarget extends EntityMessage{

    private Long time;

    public EntityMessageTarget() {
    }

    public EntityMessageTarget(Long time) {
        this.time = time;
    }

    public EntityMessageTarget(String name, String message, Long time) {
        super(name, message);
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}