package com.ajou.auction;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.ajou.auction.ApplicationClass.X_ACCESS_TOKEN;
import static com.ajou.auction.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterCeptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final Long jwtToken = sSharedPreferences.getLong(null, X_ACCESS_TOKEN);
        if (jwtToken != null) {

        }
        return chain.proceed(builder.build());
    }
}
