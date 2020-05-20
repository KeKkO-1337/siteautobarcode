package com.example.siteautobarcode.POJO;

public class User {
    private int id;
    private String username;
    private int viewed;
    private double earned;

    public User(int id,String username,int viewed,double earned)
    {
        this.id = id;
        this.username = username;
        this.viewed = viewed;
        this.earned = earned;
    }


    public int getId() {
        return id;
    }

    public double getEarned() {
        return earned;
    }

    public int getViewed() {
        return viewed;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }
}
