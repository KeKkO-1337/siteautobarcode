package com.example.siteautobarcode.POJO;

public class RowDB {
    private String ID;
    private String card;
    private String token;

    public RowDB(String ID, String card, String token)
    {
        this.ID = ID;
        this.card = card;
        this.token = token;
    }

    public String getCard() {
        return card;
    }

    public String getID() {
        return ID;
    }

    public String getToken() {
        return token;
    }
}
