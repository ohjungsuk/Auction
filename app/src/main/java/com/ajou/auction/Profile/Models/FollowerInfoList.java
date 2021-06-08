package com.ajou.auction.Profile.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FollowerInfoList {
    @SerializedName("followerNickName")
    @Expose
    private String followerNickName;

    @SerializedName("followerUserId")
    @Expose
    private String followerUserId;

    public FollowerInfoList(String followerNickName, String followerUserId) {
        this.followerNickName = followerNickName;
        this.followerUserId = followerUserId;
    }

    public String getFollowerNickName() {
        return followerNickName;
    }

    public String getFollowerUserId() {
        return followerUserId;
    }
}
