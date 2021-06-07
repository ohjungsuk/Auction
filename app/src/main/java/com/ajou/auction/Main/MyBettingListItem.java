package com.ajou.auction.Main;

import com.ajou.auction.Category.Model.BettingInfos;

import java.util.List;

public class MyBettingListItem {
    private String auctionDeadline;
    private List<BettingInfos> bettingInfos = null;
    private String boardId;
    private String category;
    private String completion;
    private String content;
    private String likeNumber;
    private String maxBettingPrice;
    private String s3imageURL;
    private String startPrice;
    private String title;
    private String priceOfThisUserBetted;
    private String writerId;
    private String writerNickName;

    // 현재참여인원 수 추가


    public MyBettingListItem(String auctionDeadline, List<BettingInfos> bettingInfos, String boardId, String category, String completion, String content, String likeNumber, String maxBettingPrice, String s3imageURL, String startPrice, String title, String priceOfThisUserBetted, String writerId, String writerNickName) {
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
        this.priceOfThisUserBetted = priceOfThisUserBetted;
        this.writerId = writerId;
        this.writerNickName = writerNickName;
    }

    public List<BettingInfos> getBettingInfos() {
        return bettingInfos;
    }

    public void setBettingInfos(List<BettingInfos> bettingInfos) {
        this.bettingInfos = bettingInfos;
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

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getS3imageURL() {
        return s3imageURL;
    }

    public void setS3imageURL(String s3imageURL) {
        this.s3imageURL = s3imageURL;
    }

    public String getPriceOfThisUserBetted() {
        return priceOfThisUserBetted;
    }

    public void setPriceOfThisUserBetted(String priceOfThisUserBetted) {
        this.priceOfThisUserBetted = priceOfThisUserBetted;
    }


    public String getAuctionDeadline() {
        return auctionDeadline;
    }

    public void setAuctionDeadline(String auctionDeadline) {
        this.auctionDeadline = auctionDeadline;
    }
}
