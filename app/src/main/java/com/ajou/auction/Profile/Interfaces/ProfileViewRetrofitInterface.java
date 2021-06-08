package com.ajou.auction.Profile.Interfaces;

import com.ajou.auction.Profile.Models.ProfileViewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileViewRetrofitInterface {
    // 사용자 프로필 가져오기
    @GET("/api/user/profile")
    Call<ProfileViewResponse> getUserProfile(@Query("userid") String userid);
}
