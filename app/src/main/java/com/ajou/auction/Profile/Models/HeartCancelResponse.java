package com.ajou.auction.Profile.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeartCancelResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;

    public HeartCancelResponse(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }
}
