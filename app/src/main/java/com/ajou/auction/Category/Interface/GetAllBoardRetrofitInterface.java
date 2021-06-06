package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.BoardListInfos;
import com.ajou.auction.Category.Model.GetAllBoardResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetAllBoardRetrofitInterface {
    @GET("/api/board/all")
    Call<GetAllBoardResponse> getAllBoard(@Query("jwt") Long jwt);
}
