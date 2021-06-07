package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeSubBoardBody {
    @SerializedName("jwt")
    @Expose
    private Long jwt;
    @SerializedName("boardId")
    @Expose
    private Long boardId;

    public LikeSubBoardBody(Long jwt,Long boardId) {
        this.jwt = jwt;
        this.boardId = boardId;

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
