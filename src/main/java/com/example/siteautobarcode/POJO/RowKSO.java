package com.example.siteautobarcode.POJO;

public class RowKSO {
    private int schet;
    private String id;
    private String card;
    private float balance;

    public RowKSO(String id, String card, float balance)
    {
        this.id = id;
        this.card = card;
        this.balance = balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSchet(int schet) {
        this.schet = schet;
    }

    public int getSchet() {
        return schet;
    }

    public RowKSO(int schet, String id)
    {
        this.schet = schet;
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public float getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }
}
