package com.ajou.auction.Post.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostS3ImageResponse {
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }
}
