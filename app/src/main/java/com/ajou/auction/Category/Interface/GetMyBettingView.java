package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.GetMyBettingResponse;

public interface GetMyBettingView {
    void getMyBettingSuccess(GetMyBettingResponse response);
    void getMyBettingFailure();
}
