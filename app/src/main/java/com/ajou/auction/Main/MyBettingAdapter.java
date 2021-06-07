package com.ajou.auction.Main;

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

import com.ajou.auction.Category.OnBoardItemClickListener;
import com.ajou.auction.Category.ViewPostListItem;
import com.ajou.auction.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyBettingAdapter extends RecyclerView.Adapter<MyBettingAdapter.ViewHolder> implements OnMyBettingItemClickListener {

    private final ArrayList<MyBettingListItem> mDataList;
    private Context mContext;
    OnMyBettingItemClickListener listener;

    public MyBettingAdapter(ArrayList<MyBettingListItem> mDataList) {
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mybettinglist, parent, false);
        return new MyBettingAdapter.ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyBettingListItem item = mDataList.get(position);

        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(700);
        mAnimation.setInterpolator(new AccelerateInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);

        Glide.with(holder.itemView).load(item.getS3imageURL()).into(holder.main_imv_s3img);
        //holder.main_tv_deadline.setText(item.getAuctionDeadline());
        holder.main_tv_mybettingprice.setText(item.getPriceOfThisUserBetted());
        holder.main_tv_title.setText(item.getTitle());
        holder.main_tv_totalbetter.setText(String.valueOf(item.getBettingInfos().size()));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }



    public void setOnMyBettingClick(OnMyBettingItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onMyBettingClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onMyBettingClick(holder,view,position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView main_tv_totalbetter,main_tv_mybettingprice,main_tv_deadline,main_tv_title;
        ImageView main_imv_s3img;
        public ViewHolder(@NonNull View itemView, final OnMyBettingItemClickListener listener) {
            super(itemView);

            main_imv_s3img = itemView.findViewById(R.id.main_imv_s3img);
            main_tv_totalbetter = itemView.findViewById(R.id.main_tv_totalbetter);
            main_tv_mybettingprice = itemView.findViewById(R.id.main_tv_mybettingprice);
            //main_tv_deadline = itemView.findViewById(R.id.main_tv_deadline);
            main_tv_title = itemView.findViewById(R.id.main_tv_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(listener != null){
                        listener.onMyBettingClick(ViewHolder.this, view, pos);
                    }
                }
            });
        }
    }
    public void clearData(){
        mDataList.clear();
    }

    public MyBettingListItem getItem(int position){
        return mDataList.get(position);
    }
    public void setItem(int position, MyBettingListItem item){
        mDataList.set(position,item);
    }
}
