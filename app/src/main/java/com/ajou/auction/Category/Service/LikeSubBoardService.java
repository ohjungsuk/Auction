package com.ajou.auction.Category.Service;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.LikeAddBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.LikeAddBoardView;
import com.ajou.auction.Category.Interface.LikeSubBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.LikeSubBoardView;
import com.ajou.auction.Category.Model.LikeAddBoardBody;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;
import com.ajou.auction.Category.Model.LikeSubBoardBody;
import com.ajou.auction.Category.Model.LikeSubBoardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikeSubBoardService {
    private final LikeSubBoardView mLikeBoardView;

    public LikeSubBoardService(final LikeSubBoardView likeSubBoardView) {
        mLikeBoardView = likeSubBoardView;
    }

    public void likeSubBoard(Long jwt,Long boardId){
        final LikeSubBoardRetrofitInterface likeSubBoardRetrofitInterface = ApplicationClass.getRetrofit2().create(LikeSubBoardRetrofitInterface.class);
        likeSubBoardRetrofitInterface.likeSubBoard(jwt,boardId).enqueue(new Callback<LikeSubBoardResponse>() {
            @Override
            public void onResponse(Call<LikeSubBoardResponse> call, Response<LikeSubBoardResponse> response) {
                LikeSubBoardResponse likeSubBoardResponse = response.body();
                mLikeBoardView.likeSubBoardSuccess(likeSubBoardResponse);
                Log.d("likeSub", "success");
            }

            @Override
            public void onFailure(Call<LikeSubBoardResponse> call, Throwable t) {
                mLikeBoardView.likeSubBoardFailure();
                Log.d("likeSub", "success");
            }
        });
    }
}
