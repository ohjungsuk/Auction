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

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.ViewHolder> {

    private final ArrayList<FollowerItem> mDataList;
    private Context mContext;

    public FollowerAdapter(ArrayList<FollowerItem> mDataList) {
        this.mDataList = mDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follow, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FollowerItem item = mDataList.get(position);

//        Glide.with(mContext).load(item.getImg()).into(holder.img);
        holder.tv_id.setText(item.getId());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView img;
        TextView tv_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            img = itemView.findViewById(R.id.follow_img_profile);
            tv_id = itemView.findViewById(R.id.follow_id);
        }
    }
}
