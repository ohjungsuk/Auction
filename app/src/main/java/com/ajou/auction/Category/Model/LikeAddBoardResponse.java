package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeAddBoardResponse {
    @SerializedName("updatedLikeNumber")
    @Expose
    private Long updatedLikeNumber;

    public Long getUpdatedLikeNumber() {
        return updatedLikeNumber;
    }

    public void setUpdatedLikeNumber(Long updatedLikeNumber) {
        this.updatedLikeNumber = updatedLikeNumber;
    }
}
