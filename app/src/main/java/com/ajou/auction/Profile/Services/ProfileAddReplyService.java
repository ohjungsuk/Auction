package com.ajou.auction.Profile.Services;

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
            }

            @Override
            public void onFailure(Call<ProfileAddReplyResponse> call, Throwable t) {
                mProfileAddReplyActivityView.addReplyFailure(null);
            }
        });
    }
}
