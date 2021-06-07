package com.ajou.auction.Category;

import com.ajou.auction.Category.Model.BettingInfos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewPostListItem {
    @SerializedName("auctionDeadline")
    @Expose
    private String auctionDeadline;
    @SerializedName("boardId")
    @Expose
    private String boardId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("completion")
    @Expose
    private String completion;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("likeNumber")
    @Expose
    private String likeNumber;
    @SerializedName("maxBettingPrice")
    @Expose
    private String maxBettingPrice;
    @SerializedName("s3imageURL")
    @Expose
    private String s3imageURL;
    @SerializedName("startPrice")
    @Expose
    private String startPrice;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("writerId")
    @Expose
    private String writerId;
    @SerializedName("writerNickName")
    @Expose
    private String writerNickName;
    @SerializedName("jwt")
    @Expose
    private String jwt;
    @SerializedName("currentUserLikeThisBoard")
    @Expose
    private Boolean currentUserLikeThisBoard;
    private List<BettingInfos> bettingInfos = null;


    public ViewPostListItem(String auctionDeadline, String boardId, String category, String completion, String content, String likeNumber, String maxBettingPrice, String s3imageURL, String startPrice, String title, String writerId, String writerNickName, String jwt, Boolean currentUserLikeThisBoard, List<BettingInfos> bettingInfos) {
        this.auctionDeadline = auctionDeadline;
        this.boardId = boardId;
        this.category = category;
        this.completion = completion;
        this.content = content;
        this.likeNumber = likeNumber;
        this.maxBettingPrice = maxBettingPrice;
        this.s3imageURL = s3imageURL;
        this.startPrice = startPrice;
        this.title = title;
        this.writerId = writerId;
        this.writerNickName = writerNickName;
        this.jwt = jwt;
        this.currentUserLikeThisBoard = currentUserLikeThisBoard;
        this.bettingInfos = bettingInfos;
    }

    public List<BettingInfos> getBettingInfos() {
        return bettingInfos;
    }

    public void setBettingInfos(List<BettingInfos> bettingInfos) {
        this.bettingInfos = bettingInfos;
    }

    public Boolean getCurrentUserLikeThisBoard() {
        return currentUserLikeThisBoard;
    }

    public void setCurrentUserLikeThisBoard(Boolean currentUserLikeThisBoard) {
        this.currentUserLikeThisBoard = currentUserLikeThisBoard;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getAuctionDeadline() {
        return auctionDeadline;
    }

    public void setAuctionDeadline(String auctionDeadline) {
        this.auctionDeadline = auctionDeadline;
    }


    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getMaxBettingPrice() {
        return maxBettingPrice;
    }

    public void setMaxBettingPrice(String maxBettingPrice) {
        this.maxBettingPrice = maxBettingPrice;
    }

    public String getS3imageURL() {
        return s3imageURL;
    }

    public void setS3imageURL(String s3imageURL) {
        this.s3imageURL = s3imageURL;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
