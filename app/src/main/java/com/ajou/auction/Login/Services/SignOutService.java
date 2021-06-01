package com.ajou.auction.Login.Services;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Login.Interfaces.LogInActivityView;
import com.ajou.auction.Login.Interfaces.LogInRetrofitInterface;
import com.ajou.auction.Login.Interfaces.SignOutActivityView;
import com.ajou.auction.Login.Interfaces.SignOutRetrofitInterface;
import com.ajou.auction.Login.Models.LogInBody;
import com.ajou.auction.Login.Models.LogInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignOutService {
    private final SignOutActivityView mSignOutActivityView;

    public SignOutService(final SignOutActivityView signOutActivityView) {
        mSignOutActivityView = signOutActivityView;
    }

    public void deleteAcc(Long jwt){
        final SignOutRetrofitInterface signOutRetrofitInterface = ApplicationClass.getRetrofit2().create(SignOutRetrofitInterface.class);
        signOutRetrofitInterface.deleteAccount(jwt).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mSignOutActivityView.validateSuccess();
                Log.d("debug_1", "STUDENT");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mSignOutActivityView.validateFailure();
            }
        });

    }
}
