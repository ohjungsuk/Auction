package com.ajou.auction.Post.Interface;

import com.ajou.auction.Post.Model.PostModifyBody;
import com.ajou.auction.Post.Model.PostModifyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface PostModifyRetrofitInterface {
    // 게시물 수정
    @PUT("/api/board")
    Call<PostModifyResponse> putUploadPost(@Body PostModifyBody params);
}
