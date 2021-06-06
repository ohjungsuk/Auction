package com.ajou.auction.Category.Service;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.DeleteMyBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Category.Interface.UpdateMyBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.UpdateMyBoardView;
import com.ajou.auction.Category.Model.UpdateMyBoardBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMyBoardService {
    private final UpdateMyBoardView mUpdateMyBoardView;

    public UpdateMyBoardService(final UpdateMyBoardView updateMyBoardView) {
        mUpdateMyBoardView = updateMyBoardView;
    }

    public void updateMyBoard(Long boardId,String completion,String content, Long jwt){
        final UpdateMyBoardRetrofitInterface updateMyBoardRetrofitInterface = ApplicationClass.getRetrofit2().create(UpdateMyBoardRetrofitInterface.class);
        updateMyBoardRetrofitInterface.updateMyBoard(new UpdateMyBoardBody(boardId,completion,content,jwt)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mUpdateMyBoardView.updateMyBoardSuccess();
                Log.d("update", "success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mUpdateMyBoardView.updateMyBoardFailure();
                Log.d("delete", "fail");
            }
        });
    }
}
