package com.ajou.auction.Profile.Interfaces;

public interface HeartActivityView {
    void sendHeartSuccess(String text);
    void sendHeartFailure(String message);

    void unsendHeartSuccess(String text);
    void unsendHeartFailure(String message);
}
