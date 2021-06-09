package com.ajou.auction.My;

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

import com.ajou.auction.Category.CategoryAdapter;
import com.ajou.auction.Category.OnBoardItemClickListener;
import com.ajou.auction.Category.ViewPostListItem;
import com.ajou.auction.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LikedListAdapter extends RecyclerView.Adapter<LikedListAdapter.ViewHolder> implements OnLikeBoardItemClickListener {

    private final ArrayList<ViewMyLikeListItem> mDataList;
    private Context mContext;
    OnLikeBoardItemClickListener listener;


    public LikedListAdapter(ArrayList<ViewMyLikeListItem> mDataList) {
        this.mDataList = mDataList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_postlist, parent, false);
        return new LikedListAdapter.ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewMyLikeListItem item = mDataList.get(position);
        
        //Glide.with(mContext).load(item.getImg()).into(holder.img);
        holder.tv_title.setText(item.getTitle());
        holder.tv_endDate.setText(item.getAuctionDeadline());
        holder.tv_price.setText(item.getMaxBettingPrice().toString());

        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(700);
        mAnimation.setInterpolator(new AccelerateInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);

        holder.tv_price.startAnimation(mAnimation);
        holder.tv_price.setTextColor(Color.RED);

        Glide.with(holder.itemView).load(item.getS3imageURL()).into(holder.img);
        holder.tv_likeCnt.setText(item.getLikeNumber().toString());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mContext = view.getContext();
//
////                SharedPreferences sharedPreferences = mContext.getSharedPreferences("boardId", Context.MODE_PRIVATE);
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                System.out.println("position " + position);
////                Long boardId = Long.valueOf(position); // boardId 받아서 저장해줘야함
////                System.out.println("longlong " + boardId);
////                editor.putLong("boardId", boardId);
////                editor.apply();
//                // 여기서는 그냥 보여주기만..
//
//                Intent intent = new Intent(mContext, ViewPostActivity.class);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void onBoardClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onBoardClick(holder,view,position);
        }
    }

    public void setOnBoardClicklistener(OnLikeBoardItemClickListener listener){
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv_title, tv_endDate, tv_price, tv_likeCnt;

        public ViewHolder(@NonNull View itemView, final OnLikeBoardItemClickListener listener) {
            super(itemView);

            img = itemView.findViewById(R.id.postlist_img);
            tv_title = itemView.findViewById(R.id.postlist_title);
            tv_endDate = itemView.findViewById(R.id.postlist_date);
            tv_price = itemView.findViewById(R.id.postlist_price);
            tv_likeCnt = itemView.findViewById(R.id.postlist_likeCnt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(listener != null){
                        listener.onBoardClick(ViewHolder.this, view, pos);
                    }
                }
            });
        }
    }
    public void clearData(){
        mDataList.clear();
    }

    public ViewMyLikeListItem getItem(int position){
        return mDataList.get(position);
    }
    public void setItem(int position, ViewMyLikeListItem item){
        mDataList.set(position,item);
    }
}
