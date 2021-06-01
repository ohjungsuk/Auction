package com.ajou.auction.Login.Services;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Login.Interfaces.LogInActivityView;
import com.ajou.auction.Login.Interfaces.LogInRetrofitInterface;
import com.ajou.auction.Login.Models.LogInBody;
import com.ajou.auction.Login.Models.LogInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInService {

    private final LogInActivityView mLogInActivityView;

    public LogInService(final LogInActivityView logInActivityView) {
        mLogInActivityView = logInActivityView;
    }

    public void postLogIn(String userId,String password){
        final LogInRetrofitInterface logInRetrofitInterface = ApplicationClass.getRetrofit2().create(LogInRetrofitInterface.class);
        logInRetrofitInterface.requestLogIn(new LogInBody(userId, password)).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                LogInResponse logInResponse = response.body();
                mLogInActivityView.validateSuccess(logInResponse);
                Log.d("debug_1", "STUDENT");
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                mLogInActivityView.validateFailure();
            }
        });

    }
}
