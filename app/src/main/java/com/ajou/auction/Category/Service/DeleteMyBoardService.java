package com.ajou.auction.Category.Service;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.DeleteMyBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Login.Interfaces.LogInActivityView;
import com.ajou.auction.Login.Interfaces.SignOutRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteMyBoardService {
    private final DeleteMyBoardView mDeleteMyBoardView;

    public DeleteMyBoardService(final DeleteMyBoardView deleteMyBoardView) {
        mDeleteMyBoardView = deleteMyBoardView;
    }

    public void deleteMyBoard(Long jwt,Long boardId){
        final DeleteMyBoardRetrofitInterface deleteMyBoardRetrofitInterface = ApplicationClass.getRetrofit2().create(DeleteMyBoardRetrofitInterface.class);
        deleteMyBoardRetrofitInterface.deleteAccount(jwt,boardId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mDeleteMyBoardView.deleteMyBoardSuccess();
                Log.d("delete", "success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mDeleteMyBoardView.deleteMyBoardFailure();
                Log.d("delete", "success");
            }
        });
    }
}
