package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.UpdateMyBoardBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface UpdateMyBoardRetrofitInterface {
    @PUT("/api/board")
    Call<Void> updateMyBoard(@Body UpdateMyBoardBody body);
}
