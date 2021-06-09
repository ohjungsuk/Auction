package com.ajou.auction.Main;

import android.view.View;

import com.ajou.auction.Category.CategoryAdapter;

public interface OnHotBoardItemClickListener {
    public void onhotBoardClick(HotBoardAdapter.ViewHolder holder, View view, int position);
}
