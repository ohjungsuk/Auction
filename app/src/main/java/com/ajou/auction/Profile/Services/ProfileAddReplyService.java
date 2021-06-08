package com.ajou.auction.Profile.Services;

import com.ajou.auction.Post.Interface.PostModifyActivityView;
import com.ajou.auction.Post.Interface.PostModifyRetrofitInterface;
import com.ajou.auction.Post.Model.PostModifyBody;
import com.ajou.auction.Post.Model.PostModifyResponse;
import com.ajou.auction.Profile.Interfaces.ProfileAddReplyActivityView;
import com.ajou.auction.Profile.Interfaces.ProfileAddReplyRetrofitInterface;
import com.ajou.auction.Profile.Models.ProfileAddReplyBody;
import com.ajou.auction.Profile.Models.ProfileAddReplyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ajou.auction.ApplicationClass.getRetrofit2;

public class ProfileAddReplyService {
    private final ProfileAddReplyActivityView mProfileAddReplyActivityView;

    public ProfileAddReplyService(ProfileAddReplyActivityView mProfileAddReplyActivityView) {
        this.mProfileAddReplyActivityView = mProfileAddReplyActivityView;
    }

    // 서버 통신
    public void postAddReply(final String content, final Long jwt, final String targetUserId) {
        final ProfileAddReplyRetrofitInterface profileAddReplyRetrofitInterface = getRetrofit2().create(ProfileAddReplyRetrofitInterface.class);
        profileAddReplyRetrofitInterface.postAddReply(new ProfileAddReplyBody(content, jwt, targetUserId)).enqueue(new Callback<ProfileAddReplyResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<ProfileAddReplyResponse> call, Response<ProfileAddReplyResponse> response) {
                final ProfileAddReplyResponse profileAddReplyResponse = response.body();
                if (profileAddReplyResponse.getReplyId() != null) {
                    mProfileAddReplyActivityView.addReplySuccess("성공");
                    System.out.println("success");
                } else {
                    System.out.println("What? " + profileAddReplyResponse.getReplyId());
                    mProfileAddReplyActivityView.addReplyFailure("실패");
                    System.out.println("faillll");
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
            public void onFailure(Call<ProfileAddReplyResponse> call, Throwable t) {
                mProfileAddReplyActivityView.addReplyFailure(null);
            }
        });
    }
}
