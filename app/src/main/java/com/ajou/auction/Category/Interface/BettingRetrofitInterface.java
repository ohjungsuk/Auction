package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.BettingBody;
import com.ajou.auction.Login.Models.SignUpBody;
import com.ajou.auction.Login.Models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BettingRetrofitInterface {
    @POST("/api/betting")
    Call<Void> betting(@Body BettingBody body);
}
