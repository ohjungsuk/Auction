package com.ajou.auction.Profile.Interfaces;

import com.ajou.auction.Profile.Models.ProfileViewResponse;
import com.ajou.auction.Profile.Models.ReplyList;

import java.util.ArrayList;

public interface ProfileViewActivityView {
    void viewProfileSuccess(ArrayList<ReplyList> dataList);
    void viewProfileFailure(String message);
}
