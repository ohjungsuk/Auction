package com.ajou.auction.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajou.auction.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProfileReviewAdapter extends RecyclerView.Adapter<ProfileReviewAdapter.ViewHolder> {

    private final ArrayList<ProfileReviewItem> mDataList;
    private Context mContext;

    public ProfileReviewAdapter(ArrayList<ProfileReviewItem> mDataList) {
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfileReviewItem item = mDataList.get(position);

        //Glide.with(mContext).load(item.getImg()).into(holder.img);
        holder.tv_id.setText(item.getId());
        holder.tv_review.setText(item.getReview());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView img;
        TextView tv_id, tv_review;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //img = itemView.findViewById(R.id.review_img_profile);
            tv_id = itemView.findViewById(R.id.review_id);
            tv_review = itemView.findViewById(R.id.review_tv);
        }
    }
}
