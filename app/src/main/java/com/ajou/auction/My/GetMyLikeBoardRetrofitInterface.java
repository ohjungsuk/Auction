package com.ajou.auction.My;

import com.ajou.auction.Category.Model.GetMyBettingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetMyLikeBoardRetrofitInterface {
    @GET("/api/user/likeboard/{jwt}")
    Call<GetMyLikeBoardResponse> getMyLike(@Path("jwt") Long jwt);
}
