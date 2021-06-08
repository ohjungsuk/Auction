package com.ajou.auction.Profile.Services;

import com.ajou.auction.Profile.Interfaces.HeartActivityView;
import com.ajou.auction.Profile.Interfaces.HeartRetrofitInterface;
import com.ajou.auction.Profile.Interfaces.ProfileViewRetrofitInterface;
import com.ajou.auction.Profile.Models.HeartCancelResponse;
import com.ajou.auction.Profile.Models.HeartResponse;
import com.ajou.auction.Profile.Models.ProfileViewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ajou.auction.ApplicationClass.getRetrofit2;

public class HeartService {
    private final HeartActivityView mHeartActivityView;

    public HeartService(HeartActivityView mHeartActivityView) {
        this.mHeartActivityView = mHeartActivityView;
    }

    // 서버 통신
    public void sendingHeart(final Long jwt, final String targetUserId) {
        final HeartRetrofitInterface heartRetrofitInterface = getRetrofit2().create(HeartRetrofitInterface.class);
        heartRetrofitInterface.sendingHeart(jwt, targetUserId).enqueue(new Callback<HeartResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<HeartResponse> call, Response<HeartResponse> response) {
                final HeartResponse heartResponse = response.body();
                if (heartResponse.getSuccess()) {
                    mHeartActivityView.sendHeartSuccess("성공이에욤");
                    System.out.println("success");
                } else {
                    System.out.println("What? " + heartResponse.getSuccess());
                    mHeartActivityView.sendHeartFailure("실패");
                    System.out.println("faillll");
                }
            }

            @Override
            public void onFailure(Call<HeartResponse> call, Throwable t) {
                mHeartActivityView.sendHeartFailure(null);
            }
        });
    }

    public void unsendingHeart(final Long jwt2, final String targetUserId2) {
        final HeartRetrofitInterface heartRetrofitInterface2 = getRetrofit2().create(HeartRetrofitInterface.class);
        heartRetrofitInterface2.unsendingHeart(jwt2, targetUserId2).enqueue(new Callback<HeartCancelResponse>() {
            // 비동기 호출
            @Override
            public void onResponse(Call<HeartCancelResponse> call, Response<HeartCancelResponse> response) {
                final HeartCancelResponse heartCancelResponse = response.body();
                if (heartCancelResponse.getSuccess()) {
                    mHeartActivityView.unsendHeartSuccess("성공이에욤");
                    System.out.println("success");
                } else {
                    System.out.println("What? " + heartCancelResponse.getSuccess());
                    mHeartActivityView.unsendHeartFailure("실패");
                    System.out.println("faillll");
                }
            }

            @Override
            public void onFailure(Call<HeartCancelResponse> call, Throwable t) {
                mHeartActivityView.unsendHeartFailure(null);
            }
        });
    }
}
