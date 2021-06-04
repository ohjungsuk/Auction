package com.ajou.auction.Post;

import android.net.Uri;

public interface ImageInterface {
    void uploadFireBaseSuccess(Uri uri);

    void uploadFireBaseFailure();
}
