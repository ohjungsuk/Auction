package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.LikeAddBoardBody;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;
import com.ajou.auction.Category.Model.UpdateMyBoardBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface LikeAddBoardRetrofitInterface {
    @GET("/api/board/likeboard/add")
    Call<LikeAddBoardResponse> likeAddBoard(@Query("jwt") Long jwt, @Query("boardId") Long boardId);
}
