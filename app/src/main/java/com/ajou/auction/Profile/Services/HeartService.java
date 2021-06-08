package com.ajou.auction.Profile.Services;

import com.ajou.auction.Profile.Interfaces.HeartActivityView;
import com.ajou.auction.Profile.Interfaces.HeartRetrofitInterface;
import com.ajou.auction.Profile.Interfaces.ProfileViewRetrofitInterface;
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
}
