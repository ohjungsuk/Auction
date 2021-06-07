package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateMyBoardBody {
    @SerializedName("boardId")
    @Expose
    private Long boardId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("jwt")
    @Expose
    private Long jwt;

    public UpdateMyBoardBody(Long boardId, String content, Long jwt) {
        this.boardId = boardId;
        this.content = content;
        this.jwt = jwt;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getJwt() {
        return jwt;
    }

    public void setJwt(Long jwt) {
        this.jwt = jwt;
    }
}
