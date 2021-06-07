package com.ajou.auction.Category;

public class RecyclerPostListItem {
    private String title;
    private String maxBettingPrice;
    private String s3imageURL;
    private String auctionDeadline;
    private String likeNumber;
    private String boardId;

    public RecyclerPostListItem(String title, String maxBettingPrice, String s3imageURL, String auctionDeadline, String likeNumber, String boardId) {
        this.title = title;
        this.maxBettingPrice = maxBettingPrice;
        this.s3imageURL = s3imageURL;
        this.auctionDeadline = auctionDeadline;
        this.likeNumber = likeNumber;
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAuctionDeadline() {
        return auctionDeadline;
    }

    public void setAuctionDeadline(String auctionDeadline) {
        this.auctionDeadline = auctionDeadline;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
