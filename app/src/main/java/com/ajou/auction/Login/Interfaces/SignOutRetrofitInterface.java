package com.ajou.auction.Login.Interfaces;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface SignOutRetrofitInterface {
    @DELETE("api/user/{jwt}")
    Call<Void> deleteAccount(@Path("jwt") Long jwt);
}
