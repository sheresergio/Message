package com.messageimposible.messageimpossible.Entity;

import com.messageimposible.messageimpossible.R;

import java.util.ArrayList;
import java.util.List;

public class EntityUsers {

    private String id;
    private int img;
    private String username;
    private String email;
    private List<EntityContact> contacts = new ArrayList<>();
    private List<EntityInvite> invites = new ArrayList<>();

    public EntityUsers() {

        this.img = R.mipmap.message_impossible_icon;

    }

    public EntityUsers(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public EntityUsers(int img, String username, String email) {
        this.img = img;
        this.username = username;
        this.email = email;
    }

    public EntityUsers(String username, String email, List<EntityContact> conatcts, List<EntityInvite> invites) {
        this.username = username;
        this.email = email;
        this.contacts = conatcts;
        this.invites = invites;
    }

    public EntityUsers(int img, String username, String email, List<EntityContact> conatcts, List<EntityInvite> invites) {
        this.img = img;
        this.username = username;
        this.email = email;
        this.contacts = conatcts;
        this.invites = invites;
    }

    public void addInvites(EntityInvite invite){

        invites.add(invite);

    }

    public void addFriends(EntityContact contact){

        contacts.add(contact);

    }

    public void deleteInvite(String email){

        int i = 0;

        for (EntityInvite invite: invites){

            if (invite.getEmail().equals(email)){

                invites.remove(i);

            }

            i++;

        }

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

    public List<EntityContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<EntityContact> conatcts) {
        this.contacts = conatcts;
    }

    public List<EntityInvite> getInvites() {
        return invites;
    }

    public void setInvites(List<EntityInvite> invites) {
        this.invites = invites;
    }
}
