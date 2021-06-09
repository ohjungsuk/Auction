package com.ajou.auction.My;

import android.view.View;

import com.ajou.auction.Category.CategoryAdapter;

public interface OnLikeBoardItemClickListener {
    public void onBoardClick(LikedListAdapter.ViewHolder holder, View view, int position);
}
