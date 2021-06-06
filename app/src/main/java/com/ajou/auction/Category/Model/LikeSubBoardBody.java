package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeSubBoardBody {
    @SerializedName("boardId")
    @Expose
    private Long boardId;
    @SerializedName("jwt")
    @Expose
    private Long jwt;

    public LikeSubBoardBody(Long boardId, Long jwt) {
        this.boardId = boardId;

        this.jwt = jwt;
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
