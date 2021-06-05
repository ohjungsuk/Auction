package com.ajou.auction.Post.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModifyResponse {
    // 서버에서 보내준 response를 변수명에 맞춰 자동으로 parsing 해준다
    @SerializedName("boardID")
    @Expose
    private Long boardID;

    public PostModifyResponse(Long boardID) {
        this.boardID = boardID;
    }

    public Long getBoardID() {
        return boardID;
    }
}
