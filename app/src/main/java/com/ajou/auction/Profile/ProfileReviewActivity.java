package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ajou.auction.Profile.Interfaces.ProfileViewActivityView;
import com.ajou.auction.Profile.Models.FollowerInfoList;
import com.ajou.auction.Profile.Models.ProfileViewResponse;
import com.ajou.auction.Profile.Models.ReplyList;
import com.ajou.auction.Profile.Services.ProfileAddReplyService;
import com.ajou.auction.Profile.Services.ProfileViewService;
import com.ajou.auction.R;

import java.util.ArrayList;

import static com.ajou.auction.ApplicationClass.jwt;

public class ProfileReviewActivity extends AppCompatActivity implements ProfileViewActivityView {

//    private ArrayList<ProfileReviewItem> dataList = new ArrayList<>();
    private ArrayList<ReplyList> replyList = new ArrayList<>();
    private Button btn_write, btn_close;
    private ProfileReviewAdapter profileReviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_review);

        btn_write = findViewById(R.id.profile_review_btn_write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
                startActivity(intent);
            }
        });

        btn_close = findViewById(R.id.profile_review_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        for (int i = 0; i < 10; i++) {
//            dataList.add(new ProfileReviewItem("", "뀨", "너무 좋았어염"));
//        }

        SharedPreferences sharedPreferences = getSharedPreferences("UserId", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userRealId", "");
        System.out.println("User " + userId + "의 리뷰보기");
        tryViewReview(userId);


        RecyclerView recyclerView = findViewById(R.id.profile_review_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        profileReviewAdapter = new ProfileReviewAdapter(replyList);
        recyclerView.setAdapter(profileReviewAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void tryViewReview(String userId) {
        final ProfileViewService profileViewService = new ProfileViewService(this);
        profileViewService.viewProfile(userId);
        System.out.println("try View review");
    }


    @Override
    public void viewProfileSuccess(ArrayList<ReplyList> dataList) {
        replyList.addAll(dataList);

        profileReviewAdapter.notifyDataSetChanged();
        System.out.println("view Profile Review success");
    }

    @Override
    public void viewProfileFailure(String message) {
        System.out.println("view Profile Review Failure");
    }

    @Override
    public void viewFollowerSuccess(ArrayList<FollowerInfoList> followerList) {

    }

    @Override
    public void viewFollowerFailure(String message) {

    }
}