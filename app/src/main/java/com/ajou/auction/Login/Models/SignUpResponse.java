package com.ajou.auction.Login.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("code")
    @Expose
    private Integer code;

    public Integer getCode() {
        return code;
    }
}
