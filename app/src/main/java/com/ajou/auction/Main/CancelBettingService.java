package com.ajou.auction.Main;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.BettingActivityView;
import com.ajou.auction.Category.Interface.BettingRetrofitInterface;
import com.ajou.auction.Category.Model.BettingBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelBettingService {
    private final CancelBettingView mCancelBettingView;

    public CancelBettingService(final CancelBettingView cancelBettingView) {
        mCancelBettingView = cancelBettingView;
    }

    public void cancelbetting(Long boardId, Long jwt){
        final CancelBettingRetrofitInterface cancelBettingRetrofitInterface = ApplicationClass.getRetrofit2().create(CancelBettingRetrofitInterface.class);
        cancelBettingRetrofitInterface.cancelbetting(new CancelBettingBody(boardId,jwt)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("cancelbetting", "success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("cancelbetting", "fail");
            }
        });
    }
}
