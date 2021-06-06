package com.ajou.auction.Post.Interface;

import com.ajou.auction.Post.Model.PostS3ImageResponse;

public interface PostS3ImageActivityView {
    void postS3UploadSuccess(PostS3ImageResponse response);
    void postS3UploadFailure();
}
