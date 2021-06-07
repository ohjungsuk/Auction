package com.ajou.auction.Login.Services;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Login.Interfaces.SignUpActivityView;
import com.ajou.auction.Login.Interfaces.SignUpRetrofitInterface;
import com.ajou.auction.Login.Models.SignUpBody;
import com.ajou.auction.Login.Models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    public SignUpService(final SignUpActivityView signUpActivityView) {
        mSignUpActivityView = signUpActivityView;
    }

    public void postSignUp(String id, String pw, String nickname ){
        final SignUpRetrofitInterface signUpRetrofitInterface = ApplicationClass.getRetrofit2().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.requestSignUp(new SignUpBody(id,pw,nickname)).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();
                if(signUpResponse != null){
                    if(response.isSuccessful()){
                        mSignUpActivityView.validateSuccess();
                    }else{
                        mSignUpActivityView.validateFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("RETRO", "HELLLO");
            }
        });
    }
}
