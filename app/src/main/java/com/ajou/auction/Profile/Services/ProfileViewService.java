package com.ajou.auction.Profile.Services;

import com.ajou.auction.Profile.Interfaces.ProfileViewActivityView;
import com.ajou.auction.Profile.Interfaces.ProfileViewRetrofitInterface;
import com.ajou.auction.Profile.Models.ProfileViewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ajou.auction.ApplicationClass.getRetrofit2;

public class ProfileViewService {
    private final ProfileViewActivityView mProfileViewActivityView;

    public ProfileViewService(ProfileViewActivityView mProfileViewActivityView) {
        this.mProfileViewActivityView = mProfileViewActivityView;
    }

    // 거래후기 관련
    public void viewProfile(final String userId) {
        final ProfileViewRetrofitInterface profileViewRetrofitInterface = getRetrofit2().create(ProfileViewRetrofitInterface.class);
        profileViewRetrofitInterface.getUserProfile(userId).enqueue(new Callback<ProfileViewResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<ProfileViewResponse> call, Response<ProfileViewResponse> response) {
                final ProfileViewResponse profileViewResponse = response.body();
                if (profileViewResponse.getUserId() != null) {
                    mProfileViewActivityView.viewProfileSuccess(profileViewResponse.getReplies());
                    System.out.println("success");
                } else {
                    System.out.println("What? " + profileViewResponse.getUserId());
                    mProfileViewActivityView.viewProfileFailure("실패");
                    System.out.println("faillll");
                }
            }

            @Override
            public void onFailure(Call<ProfileViewResponse> call, Throwable t) {
                mProfileViewActivityView.viewProfileFailure(null);
            }
        });
    }

    // 팔로워 관련
    public void viewFollower(final String userId) {
        final ProfileViewRetrofitInterface profileViewRetrofitInterface = getRetrofit2().create(ProfileViewRetrofitInterface.class);
        profileViewRetrofitInterface.getUserProfile(userId).enqueue(new Callback<ProfileViewResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<ProfileViewResponse> call, Response<ProfileViewResponse> response) {
                final ProfileViewResponse profileViewResponse = response.body();
                if (profileViewResponse.getFollowerInfos() != null) {
                    mProfileViewActivityView.viewFollowerSuccess(profileViewResponse.getFollowerInfos());
                    System.out.println("success");
                } else {
                    System.out.println("What? " + profileViewResponse.getFollowerInfos());
                    mProfileViewActivityView.viewFollowerFailure("실패");
                    System.out.println("faillll");
                }
            }

            @Override
            public void onFailure(Call<ProfileViewResponse> call, Throwable t) {
                mProfileViewActivityView.viewFollowerFailure(null);
            }
        });
    }
}
