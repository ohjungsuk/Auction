package com.ajou.auction.Profile.Models;

import com.google.gson.annotations.SerializedName;

public class ProfileAddReplyBody {
    @SerializedName("content")
    private String content;

    @SerializedName("jwt")
    private Long jwt;

    @SerializedName("targetUserId")
    private String targetUserId;

    public ProfileAddReplyBody(String content, Long jwt, String targetUserId) {
        this.content = content;
        this.jwt = jwt;
        this.targetUserId = targetUserId;
    }

    public String getContent() {
        return content;
    }

    public Long getJwt() {
        return jwt;
    }

    public String getTargetUserId() {
        return targetUserId;
    }
}
