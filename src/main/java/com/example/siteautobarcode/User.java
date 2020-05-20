package com.example.siteautobarcode;

public class User {
    private int id;
    private String username;
    private String password;
    private int viewed;
    private int earned;
    private String authority;

    public User(int id,String username,String password,int viewed,int earned,String authority)
    {
        this.authority = authority;
        this.id = id;
        this.username = username;
        this.viewed = viewed;
        this.password = password;
        this.earned = earned;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public int getEarned() {
        return earned;
    }

    public int getViewed() {
        return viewed;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }
}
