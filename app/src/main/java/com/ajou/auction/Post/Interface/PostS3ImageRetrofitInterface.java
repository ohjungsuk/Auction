package com.ajou.auction.Post.Interface;

import android.graphics.Bitmap;

import com.ajou.auction.Login.Models.LogInBody;
import com.ajou.auction.Login.Models.LogInResponse;
import com.ajou.auction.Post.Model.PostS3ImageBody;
import com.ajou.auction.Post.Model.PostS3ImageResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostS3ImageRetrofitInterface {
    @Multipart
    @POST("api/file/upload/image")
    Call<PostS3ImageResponse> UploadImage (@Part MultipartBody.Part body);
}
