package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BettingInfos {
    @SerializedName("bettedPrice")
    @Expose
    private Long bettedPrice;
    @SerializedName("nickName")
    @Expose
    private String nickName;
    @SerializedName("userId")
    @Expose
    private String userId;

    public BettingInfos(Long bettedPrice, String nickName, String userId) {
        this.bettedPrice = bettedPrice;
        this.nickName = nickName;
        this.userId = userId;
    }

    public Long getBettedPrice() {
        return bettedPrice;
    }

    public void setBettedPrice(Long bettedPrice) {
        this.bettedPrice = bettedPrice;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

