package com.ajou.auction.Category.Interface;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeleteMyBoardRetrofitInterface {
    @DELETE("api/board")
    Call<Void> deleteAccount(@Query("jwt") Long jwt, @Query("boardId") Long boardId);
}
