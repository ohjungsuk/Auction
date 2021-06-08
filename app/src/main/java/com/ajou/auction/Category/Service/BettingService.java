package com.ajou.auction.Category.Service;

import android.util.Log;
import android.widget.Toast;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.BettingActivityView;
import com.ajou.auction.Category.Interface.BettingRetrofitInterface;
import com.ajou.auction.Category.Model.BettingBody;
import com.ajou.auction.Category.ParticipateActivity;
import com.ajou.auction.Login.Interfaces.SignUpActivityView;
import com.ajou.auction.Login.Interfaces.SignUpRetrofitInterface;
import com.ajou.auction.Login.Models.SignUpBody;
import com.ajou.auction.Login.Models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BettingService {
    private final BettingActivityView mBettingActivityView;

    public BettingService(final BettingActivityView bettingActivityView) {
        mBettingActivityView = bettingActivityView;
    }

    public void betting(Long bettingPrice, Long boardId, Long jwt){
        Log.d("참여",String.valueOf(jwt));
        Log.d("참여",String.valueOf(boardId));
        final BettingRetrofitInterface bettingRetrofitInterface = ApplicationClass.getRetrofit2().create(BettingRetrofitInterface.class);
        bettingRetrofitInterface.betting(new BettingBody(bettingPrice,boardId,jwt)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mBettingActivityView.bettingSuccess();
                Log.d("betting", "success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mBettingActivityView.bettingFailure();
                Log.d("betting", "fail");
            }
        });
    }
}
