package com.ajou.auction.Main;

import android.view.View;

import com.ajou.auction.Category.CategoryAdapter;

public interface OnMyBettingItemClickListener {
    public void onMyBettingClick(MyBettingAdapter.ViewHolder holder, View view, int position);
}
