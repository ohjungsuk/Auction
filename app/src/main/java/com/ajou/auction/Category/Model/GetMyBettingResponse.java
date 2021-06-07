package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMyBettingResponse {
    @SerializedName("bettingInfos")
    @Expose
    private List<MyBettingListInfos> bettingInfos = null;

    public List<MyBettingListInfos> getBettingInfos() {
        return bettingInfos;
    }

    public void setBettingInfos(List<MyBettingListInfos> bettingInfos) {
        this.bettingInfos = bettingInfos;
    }
}
