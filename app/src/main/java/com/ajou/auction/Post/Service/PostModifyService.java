package com.ajou.auction.Post.Service;

import com.ajou.auction.Post.Interface.PostModifyActivityView;
import com.ajou.auction.Post.Interface.PostModifyRetrofitInterface;
import com.ajou.auction.Post.Interface.PostRetrofitInterface;
import com.ajou.auction.Post.Model.PostBody;
import com.ajou.auction.Post.Model.PostModifyBody;
import com.ajou.auction.Post.Model.PostModifyResponse;
import com.ajou.auction.Post.Model.PostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ajou.auction.ApplicationClass.getRetrofit2;

public class PostModifyService {
    private final PostModifyActivityView mPostModifyActivityView;

    public PostModifyService(PostModifyActivityView mPostModifyActivityView) {
        this.mPostModifyActivityView = mPostModifyActivityView;
    }

    // 서버 통신
    public void putModifyPost(final Long boardId, final String completion, final String content, final Long jwt) {
        final PostModifyRetrofitInterface postModifyRetrofitInterface = getRetrofit2().create(PostModifyRetrofitInterface.class);
        postModifyRetrofitInterface.putUploadPost(new PostModifyBody(boardId, completion, content, jwt)).enqueue(new Callback<PostModifyResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<PostModifyResponse> call, Response<PostModifyResponse> response) {
                final PostModifyResponse postModifyResponse = response.body();
                if (postModifyResponse != null) {
                    mPostModifyActivityView.postModifySuccess("성공");
                } else {
                    mPostModifyActivityView.postModifyFailure("실패");
                }
//                if (postModifyResponse == null) {
//                    mPostModifyActivityView.postModifyFailure(null);
//                    System.out.println("post 1");
//                    return;
//                }
//                else if (postModifyResponse.getBoardID() > 0) {
//                    System.out.println("post 2");
//                    mPostModifyActivityView.postModifySuccess(postModifyResponse.getBoardID().toString());
//                }
//                else {
//                    System.out.println("post 3 " + postModifyResponse.getBoardID());
//                    mPostModifyActivityView.postModifyFailure(postModifyResponse.getBoardID().toString());
//                }
            }

            @Override
            public void onFailure(Call<PostModifyResponse> call, Throwable t) {
                mPostModifyActivityView.postModifyFailure(null);
            }
        });
    }
}
