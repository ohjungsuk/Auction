package com.ajou.auction.My;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.GetMyBettingRetrofitInterface;
import com.ajou.auction.Category.Interface.GetMyBettingView;
import com.ajou.auction.Category.Model.GetMyBettingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMyLikeBoardService {
    private final GetMyLikeBoardView mGetMyLikeBoardView;

    public GetMyLikeBoardService(final GetMyLikeBoardView getMyLikeBoardView) {
        mGetMyLikeBoardView = getMyLikeBoardView;
    }

    public void getMyLike(Long jwt){
        Log.d("check", String.valueOf(jwt));
        final GetMyLikeBoardRetrofitInterface getMyLikeBoardRetrofitInterface = ApplicationClass.getRetrofit2().create(GetMyLikeBoardRetrofitInterface.class);
        getMyLikeBoardRetrofitInterface.getMyLike(jwt).enqueue(new Callback<GetMyLikeBoardResponse>() {
            @Override
            public void onResponse(Call<GetMyLikeBoardResponse> call, Response<GetMyLikeBoardResponse> response) {
                GetMyLikeBoardResponse getMyLikeBoardResponse = response.body();
                mGetMyLikeBoardView.getMyLikeSuccess(getMyLikeBoardResponse);
                Log.d("getmybetting", "success");
            }

            @Override
            public void onFailure(Call<GetMyLikeBoardResponse> call, Throwable t) {
                mGetMyLikeBoardView.getMyLikeFailure();
                Log.d("getmybetting", "fail");

            }
        });

    }
}
