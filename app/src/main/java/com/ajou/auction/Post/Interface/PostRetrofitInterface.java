package com.ajou.auction.Post.Interface;

import com.ajou.auction.Post.Model.PostBody;
import com.ajou.auction.Post.Model.PostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostRetrofitInterface {
    // 게시물 업로드
    @POST("/api/board")
    Call<PostResponse> postUploadPost(@Body PostBody params);
}
