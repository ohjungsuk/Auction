package com.ajou.auction.Post.Service;

import android.graphics.Bitmap;
import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Post.Interface.PostS3ImageActivityView;
import com.ajou.auction.Post.Interface.PostS3ImageRetrofitInterface;
import com.ajou.auction.Post.Model.PostS3ImageResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostS3ImageService {
    private final PostS3ImageActivityView mPostS3ImageActivityView;

    public PostS3ImageService(final PostS3ImageActivityView postS3ImageActivityView) {
        mPostS3ImageActivityView = postS3ImageActivityView;
    }

    public void postS3Image(MultipartBody.Part image){
        final PostS3ImageRetrofitInterface postS3ImageRetrofitInterface = ApplicationClass.getRetrofit2().create(PostS3ImageRetrofitInterface.class);
        postS3ImageRetrofitInterface.UploadImage(image).enqueue(new Callback<PostS3ImageResponse>() {
            @Override
            public void onResponse(Call<PostS3ImageResponse> call, Response<PostS3ImageResponse> response) {
                PostS3ImageResponse postS3ImageResponse = response.body();
                if (postS3ImageResponse!=null){
                    mPostS3ImageActivityView.postS3UploadSuccess(postS3ImageResponse);
                    Log.d("s3", "success");
                }else {
                    Log.d("s3", "null");
                }
            }

            @Override
            public void onFailure(Call<PostS3ImageResponse> call, Throwable t) {
                mPostS3ImageActivityView.postS3UploadFailure();
            }
        });

    }
}
