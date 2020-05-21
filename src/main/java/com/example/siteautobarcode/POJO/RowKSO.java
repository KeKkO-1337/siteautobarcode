package com.example.siteautobarcode.POJO;


public class RowKSO implements Comparable<RowKSO> {
    private int id;
    private String token;
    private String card;
    private float balance;
    private String region;

    public RowKSO(int id, String token, String card, float balance,String region)
    {
        this.id = id;
        this.token = token;
        this.card = card;
        this.balance = balance;
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RowKSO(int id, String token)
    {
        this.id = id;
        this.token = token;
    }

    public String getCard() {
        return card;
    }

    public float getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(RowKSO o) {
        int compareQuantity = ((RowKSO) o).getId();

        //ascending order
        return this.id - compareQuantity;

        //descending order
        //return compareQuantity - this.id;
    }
}
