package com.example.siteautobarcode.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MePOJO {
    @SerializedName("accountId")
    @Expose
    private long accountId;
    @SerializedName("mainIdentifier")
    @Expose
    private String mainIdentifier;
    @SerializedName("mainPointsBalance")
    @Expose
    private int mainPointsBalance;

    public int getMainPointsBalance() {
        return mainPointsBalance;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getMainIdentifier() {
        return mainIdentifier;
    }
}
