package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.LikeAddBoardResponse;

public interface LikeAddBoardView {
    void likeAddBoardSuccess(LikeAddBoardResponse response);
    void likeAddBoardFailure();
}
