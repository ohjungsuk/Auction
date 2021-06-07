package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BettingBody {
    @SerializedName("bettingPrice")
    @Expose
    private Long bettingPrice;
    @SerializedName("boardId")
    @Expose
    private Long boardId;
    @SerializedName("jwt")
    @Expose
    private Long jwt;

    public BettingBody(Long bettingPrice, Long boardId, Long jwt) {
        this.bettingPrice = bettingPrice;
        this.boardId = boardId;
        this.jwt = jwt;
    }

    public Long getBettingPrice() {
        return bettingPrice;
    }

    public void setBettingPrice(Long bettingPrice) {
        this.bettingPrice = bettingPrice;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getJwt() {
        return jwt;
    }

    public void setJwt(Long jwt) {
        this.jwt = jwt;
    }
}
