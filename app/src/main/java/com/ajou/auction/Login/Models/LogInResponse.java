package com.ajou.auction.Login.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInResponse {
    @SerializedName("jwt")
    @Expose
    private Long jwt;
    @SerializedName("code")
    @Expose
    private Integer code;

    public Long getJwt() {
        return jwt;
    }

    public void setJwt(Long jwt) {
        this.jwt = jwt;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
