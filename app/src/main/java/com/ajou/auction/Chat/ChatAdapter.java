package com.ajou.auction.Chat;

import android.content.Context;
import android.content.Intent;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private final ArrayList<ChatItem> mDataList;
    private Context mContext;

    public ChatAdapter(ArrayList<ChatItem> mDataList) {
        this.mDataList = mDataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatItem item = mDataList.get(position);

        holder.tv_sender.setText(item.getSender());
        holder.tv_message.setText(item.getMessage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext = view.getContext();

                Intent intent = new Intent(mContext, ChatActivity.class);
                mContext.startActivity(intent);
            }
        });
//        holder.img_profile.setImageResource(R.drawable.icon_auction);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView img_profile;
        TextView tv_sender, tv_message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_sender = itemView.findViewById(R.id.chat_txt_name);
            tv_message = itemView.findViewById(R.id.chat_txt_intro);
//            img_profile = itemView.findViewById(R.id.chat_img_profile);
        }
    }
}
