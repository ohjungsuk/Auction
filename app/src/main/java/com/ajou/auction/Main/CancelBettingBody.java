package com.ajou.auction.Main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelBettingBody {
    @SerializedName("boardId")
    @Expose
    private Long boardId;
    @SerializedName("jwt")
    @Expose
    private Long jwt;

    public CancelBettingBody(Long boardId, Long jwt) {
        this.boardId = boardId;
        this.jwt = jwt;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
