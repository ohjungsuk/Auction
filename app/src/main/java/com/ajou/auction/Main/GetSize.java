package com.ajou.auction.Main;

import com.ajou.auction.Category.Model.BettingInfos;

import java.util.List;

public class GetSize {
    private List<BettingInfos> bettingInfos = null;

    public GetSize(List<BettingInfos> bettingInfos) {
        this.bettingInfos = bettingInfos;
    }

    public List<BettingInfos> getBettingInfos() {
        return bettingInfos;
    }

    public void setBettingInfos(List<BettingInfos> bettingInfos) {
        this.bettingInfos = bettingInfos;
    }
}
