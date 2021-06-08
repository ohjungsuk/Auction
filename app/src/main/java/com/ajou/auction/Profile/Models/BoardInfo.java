package com.ajou.auction.Profile.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BoardInfo {
    @SerializedName("auctionDeadline")
    @Expose
    private String auctionDeadline;

    @SerializedName("bettingInfos")
    @Expose
    private ArrayList<BettingInfoList> bettingInfos;

    @SerializedName("boardId")
    @Expose
    private Long boardId;

    @SerializedName("category")
    @Expose
    private Long category;

    @SerializedName("completion")
    @Expose
    private String completion;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("likeNumber")
    @Expose
    private Long likeNumber;

    @SerializedName("maxBettingPrice")
    @Expose
    private Long maxBettingPrice;

    @SerializedName("s3imageURL")
    @Expose
    private String s3imageURL;

    @SerializedName("startPrice")
    @Expose
    private Long startPrice;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("uploadDate")
    @Expose
    private String uploadDate;

    @SerializedName("writerId")
    @Expose
    private String writerId;

    @SerializedName("writerNickName")
    @Expose
    private String writerNickName;

    public BoardInfo(String auctionDeadline, ArrayList<BettingInfoList> bettingInfos, Long boardId, Long category, String completion, String content, Long likeNumber, Long maxBettingPrice, String s3imageURL, Long startPrice, String title, String uploadDate, String writerId, String writerNickName) {
        this.auctionDeadline = auctionDeadline;
        this.bettingInfos = bettingInfos;
        this.boardId = boardId;
        this.category = category;
        this.completion = completion;
        this.content = content;
        this.likeNumber = likeNumber;
        this.maxBettingPrice = maxBettingPrice;
        this.s3imageURL = s3imageURL;
        this.startPrice = startPrice;
        this.title = title;
        this.uploadDate = uploadDate;
        this.writerId = writerId;
        this.writerNickName = writerNickName;
    }

    public String getAuctionDeadline() {
        return auctionDeadline;
    }

    public ArrayList<BettingInfoList> getBettingInfos() {
        return bettingInfos;
    }

    public Long getBoardId() {
        return boardId;
    }

    public Long getCategory() {
        return category;
    }

    public String getCompletion() {
        return completion;
    }

    public String getContent() {
        return content;
    }

    public Long getLikeNumber() {
        return likeNumber;
    }

    public Long getMaxBettingPrice() {
        return maxBettingPrice;
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

    public String getUploadDate() {
        return uploadDate;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getWriterNickName() {
        return writerNickName;
    }
}
