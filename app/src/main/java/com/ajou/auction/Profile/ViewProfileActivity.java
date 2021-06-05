package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Profile.ProfileReviewActivity;
import com.ajou.auction.R;

public class ViewProfileActivity extends AppCompatActivity {

    private String userId;
    private TextView tv_id, tv_jjim, tv_selling, tv_review;
    private Button btn_follow, btn_unfollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
        System.out.println("User Id 확인 " + userId);

        tv_id = findViewById(R.id.view_profile_tv_id);
        tv_id.setText(userId);

        tv_jjim = findViewById(R.id.view_profile_tv_jjim);
        tv_jjim.setOnClickListener(new View.OnClickListener() { // 찜한 게시글
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryListActivity.class);
                startActivity(intent);
            }
        });

        tv_selling = findViewById(R.id.view_profile_tv_selling);
        tv_selling.setOnClickListener(new View.OnClickListener() { // 판매 상품
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoryListActivity.class);
                startActivity(intent);
            }
        });

        tv_review = findViewById(R.id.view_profile_tv_review);
        tv_review.setOnClickListener(new View.OnClickListener() { // 거래 후기
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileReviewActivity.class);
                startActivity(intent);
            }
        });

        btn_follow = findViewById(R.id.view_profile_btn_follow);

        btn_unfollow = findViewById(R.id.view_profile_btn_unfollow);


    }
}