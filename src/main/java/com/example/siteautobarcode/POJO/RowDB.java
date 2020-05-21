package com.example.siteautobarcode.POJO;

public class RowDB {
    private String ID;
    private String card;
    private String token;
    private int balance;

    public RowDB(String ID, String card, String token, int balance)
    {
        this.ID = ID;
        this.card = card;
        this.token = token;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
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
