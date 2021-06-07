package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class MyBettingListInfos {
    @SerializedName("boardOfThisUserBetted")
    @Expose
    private MyBettingBoardListInfos boardOfThisUserBetted;

    @SerializedName("priceOfThisUserBetted")
    @Expose
    private Long priceOfThisUserBetted;

    public MyBettingBoardListInfos getBoardOfThisUserBetted() {
        return boardOfThisUserBetted;
    }

    public void setBoardOfThisUserBetted(MyBettingBoardListInfos boardOfThisUserBetted) {
        this.boardOfThisUserBetted = boardOfThisUserBetted;
    }

    public Long getPriceOfThisUserBetted() {
        return priceOfThisUserBetted;
    }

    public void setPriceOfThisUserBetted(Long priceOfThisUserBetted) {
        this.priceOfThisUserBetted = priceOfThisUserBetted;
    }
}
