package com.ajou.auction.Profile.Interfaces;

import com.ajou.auction.Profile.Models.ProfileAddReplyBody;
import com.ajou.auction.Profile.Models.ProfileAddReplyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProfileAddReplyRetrofitInterface {
    // 댓글 추가
    @POST("/api/user/reply")
    Call<ProfileAddReplyResponse> postAddReply(@Body ProfileAddReplyBody params);
}
