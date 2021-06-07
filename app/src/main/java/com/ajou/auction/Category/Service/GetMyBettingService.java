package com.ajou.auction.Category.Service;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.GetMyBettingRetrofitInterface;
import com.ajou.auction.Category.Interface.GetMyBettingView;
import com.ajou.auction.Category.Model.GetMyBettingResponse;
import com.ajou.auction.Login.Interfaces.SignOutActivityView;
import com.ajou.auction.Login.Interfaces.SignOutRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMyBettingService {
    private final GetMyBettingView mGetMyBettingView;

    public GetMyBettingService(final GetMyBettingView getMyBettingView) {
        mGetMyBettingView = getMyBettingView;
    }

    public void getMybetting(Long jwt){
        Log.d("check", String.valueOf(jwt));
        final GetMyBettingRetrofitInterface getMyBettingRetrofitInterface = ApplicationClass.getRetrofit2().create(GetMyBettingRetrofitInterface.class);
        getMyBettingRetrofitInterface.getMyBetting(jwt).enqueue(new Callback<GetMyBettingResponse>() {
            @Override
            public void onResponse(Call<GetMyBettingResponse> call, Response<GetMyBettingResponse> response) {
                GetMyBettingResponse getMyBettingResponse = response.body();
                mGetMyBettingView.getMyBettingSuccess(getMyBettingResponse);
                Log.d("getmybetting", "success");
            }

            @Override
            public void onFailure(Call<GetMyBettingResponse> call, Throwable t) {
                mGetMyBettingView.getMyBettingFailure();
                Log.d("getmybetting", "fail");

            }
        });

    }
}
