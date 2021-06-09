package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.BettingCompleteResponse;
import com.ajou.auction.Category.Model.GetMyBettingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BettingCompleteRetrofitInterface {
    @GET("/api/board/complete")
    Call<BettingCompleteResponse> completeBetting(@Query("jwt") Long jwt, @Query("boardId") Long boardId);
}
