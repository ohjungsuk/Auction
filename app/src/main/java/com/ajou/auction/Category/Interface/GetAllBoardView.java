package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.BoardListInfos;
import com.ajou.auction.Category.Model.GetAllBoardResponse;

public interface GetAllBoardView {
    void getAllBoardSuccess(GetAllBoardResponse response);
    void getAllBoardFailure();
}
