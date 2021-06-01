package com.ajou.auction.Login.Interfaces;

import com.ajou.auction.Login.Models.SignUpBody;
import com.ajou.auction.Login.Models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    @POST("/api/user/register")
    Call<SignUpResponse> requestSignUp(@Body SignUpBody body);
}
