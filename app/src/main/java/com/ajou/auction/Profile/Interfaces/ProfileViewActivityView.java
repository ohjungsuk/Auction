package com.ajou.auction.Profile.Interfaces;

import com.ajou.auction.Profile.Models.BoardInfo;
import com.ajou.auction.Profile.Models.FollowerInfoList;
import com.ajou.auction.Profile.Models.ProfileViewResponse;
import com.ajou.auction.Profile.Models.ReplyList;

import java.util.ArrayList;

public interface ProfileViewActivityView {
    // 거래후기 댓글 관련
    void viewProfileSuccess(ArrayList<ReplyList> dataList);
    void viewProfileFailure(String message);

    // 팔로워 보기 관련
    void viewFollowerSuccess(ArrayList<FollowerInfoList> followerList);
    void viewFollowerFailure(String message);

    // 판매 상품 관련
    void viewProductSuccess(ArrayList<BoardInfo> boardList,ProfileViewResponse response);
    void viewProductSuccess2(ArrayList<BoardInfo> boardList);
    void viewProductFailure(String message);
}
