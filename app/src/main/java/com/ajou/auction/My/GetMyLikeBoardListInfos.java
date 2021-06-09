package com.ajou.auction.My;

import com.ajou.auction.Category.Model.BettingInfos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMyLikeBoardListInfos {
    @SerializedName("auctionDeadline")
    @Expose
    private String auctionDeadline;
    @SerializedName("bettingInfos")
    @Expose
    private List<BettingInfos> bettingInfos = null;
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


    public String getAuctionDeadline() {
        return auctionDeadline;
    }

    public void setAuctionDeadline(String auctionDeadline) {
        this.auctionDeadline = auctionDeadline;
    }

    public List<BettingInfos> getBettingInfos() {
        return bettingInfos;
    }

    public void setBettingInfos(List<BettingInfos> bettingInfos) {
        this.bettingInfos = bettingInfos;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Long likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Long getMaxBettingPrice() {
        return maxBettingPrice;
    }

    public void setMaxBettingPrice(Long maxBettingPrice) {
        this.maxBettingPrice = maxBettingPrice;
    }

    public String getS3imageURL() {
        return s3imageURL;
    }

    public void setS3imageURL(String s3imageURL) {
        this.s3imageURL = s3imageURL;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getWriterNickName() {
        return writerNickName;
    }

    public void setWriterNickName(String writerNickName) {
        this.writerNickName = writerNickName;
    }
}
