package com.ajou.auction.Category.Service;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.BettingCompleteRetrofitInterface;
import com.ajou.auction.Category.Interface.BettingCompleteView;
import com.ajou.auction.Category.Interface.LikeAddBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.LikeAddBoardView;
import com.ajou.auction.Category.Model.BettingCompleteResponse;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BettingCompleteService {
    private final BettingCompleteView mBettingCompleteView;

    public BettingCompleteService(final BettingCompleteView bettingCompleteView) {
        mBettingCompleteView = bettingCompleteView;
    }

    public void completeBetting(Long jwt,Long boardId){
        final BettingCompleteRetrofitInterface bettingCompleteRetrofitInterface = ApplicationClass.getRetrofit2().create(BettingCompleteRetrofitInterface.class);
        bettingCompleteRetrofitInterface.completeBetting(jwt,boardId).enqueue(new Callback<BettingCompleteResponse>() {
            @Override
            public void onResponse(Call<BettingCompleteResponse> call, Response<BettingCompleteResponse> response) {
                BettingCompleteResponse bettingCompleteResponse = response.body();
                mBettingCompleteView.completeBettingSuccess(bettingCompleteResponse);
                Log.d("likeadd", "success");
            }

            @Override
            public void onFailure(Call<BettingCompleteResponse> call, Throwable t) {
                mBettingCompleteView.completeBettingFailure();
                Log.d("likeadd", "success");
            }
        });
    }
}
