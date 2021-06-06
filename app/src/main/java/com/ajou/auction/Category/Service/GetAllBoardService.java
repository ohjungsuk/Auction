package com.ajou.auction.Category.Service;

import android.util.Log;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Category.Interface.GetAllBoardRetrofitInterface;
import com.ajou.auction.Category.Interface.GetAllBoardView;
import com.ajou.auction.Category.Model.BoardListInfos;
import com.ajou.auction.Category.Model.GetAllBoardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllBoardService {
    private final GetAllBoardView mgetAllBoardView;

    public GetAllBoardService(final GetAllBoardView getAllBoardView) {
        mgetAllBoardView = getAllBoardView;
    }

    public void getAllBoard(Long jwt){
        final GetAllBoardRetrofitInterface getAllBoardRetrofitInterface = ApplicationClass.getRetrofit2().create(GetAllBoardRetrofitInterface.class);
        getAllBoardRetrofitInterface.getAllBoard(jwt).enqueue(new Callback<GetAllBoardResponse>() {
            @Override
            public void onResponse(Call<GetAllBoardResponse> call, Response<GetAllBoardResponse> response) {
                GetAllBoardResponse getAllBoardResponse = response.body();
                mgetAllBoardView.getAllBoardSuccess(getAllBoardResponse);
                Log.d("getboard","success!");
            }
            @Override
            public void onFailure(Call<GetAllBoardResponse> call, Throwable t) {
                mgetAllBoardView.getAllBoardFailure();
                Log.d("getboard","failure");
            }
        });
    }
}
