package com.ajou.auction.Main;

import com.ajou.auction.Category.Model.BettingBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CancelBettingRetrofitInterface {
    @DELETE("/api/betting")
    Call<Void> cancelbetting(@Query("jwt") Long jwt, @Query("boardId") Long boardId);
}
