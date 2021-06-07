package com.ajou.auction.Category.Service;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.DeleteMyBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Category.Interface.LikeAddBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.LikeAddBoardView;
import com.ajou.auction.Category.Model.LikeAddBoardBody;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeAddBoardService {
    private final LikeAddBoardView mLikeBoardView;

    public LikeAddBoardService(final LikeAddBoardView likeAddBoardView) {
        mLikeBoardView = likeAddBoardView;
    }

    public void likeAddBoard(Long jwt,Long boardId){
        final LikeAddBoardRetrofitInterface likeAddBoardRetrofitInterface = ApplicationClass.getRetrofit2().create(LikeAddBoardRetrofitInterface.class);
        likeAddBoardRetrofitInterface.likeAddBoard(jwt,boardId).enqueue(new Callback<LikeAddBoardResponse>() {
            @Override
            public void onResponse(Call<LikeAddBoardResponse> call, Response<LikeAddBoardResponse> response) {
                LikeAddBoardResponse likeAddBoardResponse = response.body();
                mLikeBoardView.likeAddBoardSuccess(likeAddBoardResponse);
                Log.d("likeadd", "success");
            }

            @Override
            public void onFailure(Call<LikeAddBoardResponse> call, Throwable t) {
                mLikeBoardView.likeAddBoardFailure();
                Log.d("likeadd", "success");
            }
        });
    }
}
