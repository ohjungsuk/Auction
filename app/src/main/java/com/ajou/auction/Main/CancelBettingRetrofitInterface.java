package com.ajou.auction.Main;

import com.ajou.auction.Category.Model.BettingBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

public interface CancelBettingRetrofitInterface {
    @DELETE("/api/betting")
    Call<Void> cancelbetting(@Body CancelBettingBody body);
}
