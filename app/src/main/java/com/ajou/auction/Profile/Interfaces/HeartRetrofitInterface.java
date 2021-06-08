package com.ajou.auction.Profile.Interfaces;

import com.ajou.auction.Profile.Models.HeartCancelResponse;
import com.ajou.auction.Profile.Models.HeartResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeartRetrofitInterface {
    // 팔로잉 추가 (하트 보내기)
    @GET("/api/user/follow/add")
    Call<HeartResponse> sendingHeart(@Query("jwt") Long jwt, @Query("targetUserId") String targetUserId);

    // 팔로잉 취소 (하트 회수)
    @GET("/api/user/follow/sub")
    Call<HeartCancelResponse> unsendingHeart(@Query("jwt") Long jwt, @Query("targetUserId") String targetUserId);
}
