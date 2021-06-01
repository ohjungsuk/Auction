package com.ajou.auction.Login.Interfaces;

import com.ajou.auction.Login.Models.LogInBody;
import com.ajou.auction.Login.Models.LogInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LogInRetrofitInterface {
    @POST("api/user/login")
    Call<LogInResponse> requestLogIn (@Body LogInBody body);
}
