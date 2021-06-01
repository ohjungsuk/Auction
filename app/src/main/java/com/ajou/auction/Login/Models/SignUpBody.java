package com.ajou.auction.Login.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpBody {
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("nickName")
    @Expose
    private String nickName;

    public SignUpBody(String userId, String password, String nickName) {
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
