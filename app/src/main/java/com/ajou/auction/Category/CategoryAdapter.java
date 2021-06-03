package com.ajou.auction.Category;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajou.auction.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final ArrayList<CategoryListItem> mDataList;
    private Context mContext;

    public CategoryAdapter(ArrayList<CategoryListItem> mDataList) {
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_postlist, parent, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryListItem item = mDataList.get(position);
        
        //Glide.with(mContext).load(item.getImg()).into(holder.img);
        holder.tv_title.setText(item.getTitle());
        holder.tv_endDate.setText(item.getEndDate());
        holder.tv_price.setText(item.getPrice());

        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(700);
        mAnimation.setInterpolator(new AccelerateInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);

        holder.tv_price.startAnimation(mAnimation);
        holder.tv_price.setTextColor(Color.RED);



        holder.tv_likeCnt.setText(item.getLikeCnt());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView img;
        TextView tv_title, tv_endDate, tv_price, tv_likeCnt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //img = itemView.findViewById(R.id.postlist_img);
            tv_title = itemView.findViewById(R.id.postlist_title);
            tv_endDate = itemView.findViewById(R.id.postlist_date);
            tv_price = itemView.findViewById(R.id.postlist_price);
            tv_likeCnt = itemView.findViewById(R.id.postlist_likeCnt);
        }
    }
}
