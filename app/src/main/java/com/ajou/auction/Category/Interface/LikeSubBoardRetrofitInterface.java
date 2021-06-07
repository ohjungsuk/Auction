package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.LikeAddBoardBody;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;
import com.ajou.auction.Category.Model.LikeSubBoardBody;
import com.ajou.auction.Category.Model.LikeSubBoardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface LikeSubBoardRetrofitInterface {
    @GET("/api/board/likeboard/sub")
    Call<LikeSubBoardResponse> likeSubBoard(@Query("jwt") Long jwt, @Query("boardId") Long boardId);
}
