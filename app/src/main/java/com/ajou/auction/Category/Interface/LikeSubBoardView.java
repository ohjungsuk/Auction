package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.LikeSubBoardResponse;

public interface LikeSubBoardView {
    void likeSubBoardSuccess(LikeSubBoardResponse response);
    void likeSubBoardFailure();
}
