package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.BettingCompleteResponse;

public interface BettingCompleteView {
    void completeBettingSuccess(BettingCompleteResponse response);
    void completeBettingFailure();
}
