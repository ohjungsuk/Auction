package com.ajou.auction.Login.Interfaces;

import com.ajou.auction.Login.Models.LogInResponse;

public interface LogInActivityView {
    void validateFailure();
    void validateSuccess(LogInResponse response);
}
