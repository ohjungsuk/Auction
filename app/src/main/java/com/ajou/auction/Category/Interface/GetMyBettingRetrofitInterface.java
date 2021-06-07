package com.ajou.auction.Category.Interface;

import com.ajou.auction.Category.Model.BettingBody;
import com.ajou.auction.Category.Model.GetMyBettingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetMyBettingRetrofitInterface {
    @GET("/api/betting/{jwt}")
    Call<GetMyBettingResponse> getMyBetting(@Path("jwt") Long jwt);
}
