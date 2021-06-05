package com.ajou.auction.Post.Model;

import com.google.gson.annotations.SerializedName;

public class PostBody {
    /*
    {
  "auctionDeadline": "string",
  "category": 0,
  "content": "string",
  "jwt": 0,
  "s3imageURL": "string",
  "startPrice": 0,
  "title": "string"
}
     */
    @SerializedName("auctionDeadline")
    private String auctionDeadLine;

    @SerializedName("category")
    private Long category;

    @SerializedName("content")
    private String content;

    @SerializedName("jwt")
    private Long jwt;

    @SerializedName("s3imageURL")
    private String s3imageURL;

    @SerializedName("startPrice")
    private Long startPrice;

    @SerializedName("title")
    private String title;

    public PostBody(String auctionDeadLine, Long category, String content, Long jwt, String s3imageURL, Long startPrice, String title) {
        this.auctionDeadLine = auctionDeadLine;
        this.category = category;
        this.content = content;
        this.jwt = jwt;
        this.s3imageURL = s3imageURL;
        this.startPrice = startPrice;
        this.title = title;
    }

    public String getAuctionDeadLine() {
        return auctionDeadLine;
    }

    public Long getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public Long getJwt() {
        return jwt;
    }

    public String getS3imageURL() {
        return s3imageURL;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public String getTitle() {
        return title;
    }
}
