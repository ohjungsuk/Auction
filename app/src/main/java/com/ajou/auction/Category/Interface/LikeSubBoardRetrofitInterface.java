package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.LikeAddBoardBody;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;
import com.ajou.auction.Category.Model.LikeSubBoardBody;
import com.ajou.auction.Category.Model.LikeSubBoardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface LikeSubBoardRetrofitInterface {
    @PUT("/api/board/likeboard/sub")
    Call<LikeSubBoardResponse> likeSubBoard(@Body LikeSubBoardBody body);
}
