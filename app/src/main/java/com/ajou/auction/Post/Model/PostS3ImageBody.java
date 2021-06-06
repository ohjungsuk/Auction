package com.ajou.auction.Post.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostS3ImageBody {
    @SerializedName("image")
    @Expose
    private String image;

    public PostS3ImageBody(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
