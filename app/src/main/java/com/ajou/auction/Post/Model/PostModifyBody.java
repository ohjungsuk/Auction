package com.ajou.auction.Post.Model;

import com.google.gson.annotations.SerializedName;

public class PostModifyBody {
    /*
    {
  "boardId": 0,
  "completion": "string",
  "content": "string",
  "jwt": 0
}
     */
    @SerializedName("boardId")
    private Long boardId;

    @SerializedName("completion")
    private String completion;

    @SerializedName("content")
    private String content;

    @SerializedName("jwt")
    private Long jwt;

    public PostModifyBody(Long boardId, String completion, String content, Long jwt) {
        this.boardId = boardId;
        this.completion = completion;
        this.content = content;
        this.jwt = jwt;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getCompletion() {
        return completion;
    }

    public String getContent() {
        return content;
    }

    public Long getJwt() {
        return jwt;
    }
}
