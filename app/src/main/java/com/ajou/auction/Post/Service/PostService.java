package com.ajou.auction.Post.Service;

import com.ajou.auction.Post.Interface.PostActivityView;
import com.ajou.auction.Post.Interface.PostRetrofitInterface;
import com.ajou.auction.Post.Model.PostBody;
import com.ajou.auction.Post.Model.PostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ajou.auction.ApplicationClass.getRetrofit2;

public class PostService {
    private final PostActivityView mPostActivityView;

    public PostService(PostActivityView mPostActivityView) {
        this.mPostActivityView = mPostActivityView;
    }


    /*

    LogInResponse logInResponse = response.body();
                mLogInActivityView.validateSuccess(logInResponse);
                Log.d("debug_1", "STUDENT");
     */
    // 서버 통신
    public void postUploadPost(final String auctionDeadLine, final Long category, final String content, final Long jwt, final String s3imageURL, final Long startPrice, final String title) {
        final PostRetrofitInterface postRetrofitInterface = getRetrofit2().create(PostRetrofitInterface.class);
        postRetrofitInterface.postUploadPost(new PostBody(auctionDeadLine, category, content, jwt, s3imageURL, startPrice, title)).enqueue(new Callback<PostResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                final PostResponse postResponse = response.body();
                if (postResponse == null) {
                    mPostActivityView.postFailure(null);
                    System.out.println("post 1");
                    return;
                }
                else if (postResponse.getBoardID() > 0) {
                    System.out.println("post 2");
                    mPostActivityView.postSuccess(postResponse.getBoardID().toString());
                }
                else {
                    System.out.println("post 3 " + postResponse.getBoardID());
                    mPostActivityView.postFailure(postResponse.getBoardID().toString());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                mPostActivityView.postFailure(null);
            }
        });
    }
}
