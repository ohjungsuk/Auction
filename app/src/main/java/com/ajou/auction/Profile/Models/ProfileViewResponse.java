package com.ajou.auction.Profile.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfileViewResponse {
    /*
    {
  "boardInfos": [
    {
      "auctionDeadline": "string",
      "bettingInfos": [
        {
          "bettedPrice": 0,
          "nickName": "string",
          "userId": "string"
        }
      ],
      "boardId": 0,
      "category": 0,
      "completion": "string",
      "content": "string",
      "likeNumber": 0,
      "maxBettingPrice": 0,
      "s3imageURL": "string",
      "startPrice": 0,
      "title": "string",
      "uploadDate": "string",
      "writerId": "string",
      "writerNickName": "string"
    }
  ],
  "followerInfos": [
    {
      "followerNickName": "string",
      "followerUserId": "string"
    }
  ],
  "nickName": "string",
  "replies": [
    {
      "content": "string",
      "uploadDate": "string",
      "writerNickName": "string",
      "writerUserId": "string"
    }
  ],
  "userId": "string"
}
     */

    @SerializedName("boardInfos")
    @Expose
    private ArrayList<BoardInfo> boardInfos;

    @SerializedName("followerInfos")
    @Expose
    private ArrayList<FollowerInfoList> followerInfos;

    @SerializedName("nickName")
    @Expose
    private String nickName;

    @SerializedName("replies")
    @Expose
    private ArrayList<ReplyList> replies;

    @SerializedName("userId")
    @Expose
    private String userId;

    public ProfileViewResponse(ArrayList<BoardInfo> boardInfos, ArrayList<FollowerInfoList> followerInfos, String nickName, ArrayList<ReplyList> replies, String userId) {
        this.boardInfos = boardInfos;
        this.followerInfos = followerInfos;
        this.nickName = nickName;
        this.replies = replies;
        this.userId = userId;
    }

    public ArrayList<BoardInfo> getBoardInfos() {
        return boardInfos;
    }

    public ArrayList<FollowerInfoList> getFollowerInfos() {
        return followerInfos;
    }

    public String getNickName() {
        return nickName;
    }

    public ArrayList<ReplyList> getReplies() {
        return replies;
    }

    public String getUserId() {
        return userId;
    }
}
