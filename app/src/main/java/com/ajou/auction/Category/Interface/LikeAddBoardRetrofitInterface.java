package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.LikeAddBoardBody;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;
import com.ajou.auction.Category.Model.UpdateMyBoardBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface LikeAddBoardRetrofitInterface {
    @PUT("/api/board/likeboard/add")
    Call<LikeAddBoardResponse> likeAddBoard(@Body LikeAddBoardBody body);
}
