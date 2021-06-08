package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajou.auction.Profile.Interfaces.ProfileViewActivityView;
import com.ajou.auction.Profile.Models.FollowerInfoList;
import com.ajou.auction.Profile.Models.ReplyList;
import com.ajou.auction.Profile.Services.ProfileAddReplyService;
import com.ajou.auction.Profile.Services.ProfileViewService;
import com.ajou.auction.R;

import java.util.ArrayList;

public class FollowerActivity extends AppCompatActivity implements ProfileViewActivityView {

    private Button btn_close;
//    private ArrayList<FollowerItem> dataList = new ArrayList<>();
    private ArrayList<FollowerInfoList> followerInfoLists = new ArrayList<>();
    private FollowerAdapter followerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);

        btn_close = findViewById(R.id.follower_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        for (int i = 0; i < 10; i++) {
//            dataList.add(new FollowerItem("", "아이디.."));
//        }

        // 유저 ID 저장
        SharedPreferences sharedPreferences = getSharedPreferences("ID", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", "");
        System.out.println("로그인한 유저의 Id 확인 " + userId);

        tryViewFollower(userId);

        RecyclerView recyclerView = findViewById(R.id.follower_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        followerAdapter = new FollowerAdapter(followerInfoLists);
        recyclerView.setAdapter(followerAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void tryViewFollower(String targetUserId) {
        final ProfileViewService profileViewService = new ProfileViewService(this);
        profileViewService.viewFollower(targetUserId);
        System.out.println("try View Follower");
    }

    @Override
    public void viewProfileSuccess(ArrayList<ReplyList> dataList) {

    }

    @Override
    public void viewProfileFailure(String message) {

    }

    @Override
    public void viewFollowerSuccess(ArrayList<FollowerInfoList> followerList) {
        followerInfoLists.addAll(followerList);

        followerAdapter.notifyDataSetChanged();
        System.out.println("view Profile Follower success");
    }

    @Override
    public void viewFollowerFailure(String message) {
        System.out.println("view Profile Follower Failure");
    }
}