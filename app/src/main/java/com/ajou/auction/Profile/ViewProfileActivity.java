package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ajou.auction.Profile.Interfaces.HeartActivityView;
import com.ajou.auction.Profile.Services.HeartService;
import com.ajou.auction.R;
import static com.ajou.auction.ApplicationClass.jwt;

public class ViewProfileActivity extends AppCompatActivity implements HeartActivityView {

    private String userId;
    private TextView tv_id, tv_jjim, tv_selling, tv_review;
    private Button btn_follow, btn_unfollow, btn_close;
    public static boolean forName = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
        System.out.println("현재 프로필을 보려고 하는 User 닉네임 " + userId);

        sharedPreferences = getSharedPreferences("UserId", Context.MODE_PRIVATE);
        String userRealId = sharedPreferences.getString("userRealId", "");
        System.out.println("User Real Id 확인 " + userRealId);
        System.out.println("content (jwt) " + jwt);

        tv_id = findViewById(R.id.view_profile_tv_id);
        tv_id.setText(userId);

        tv_jjim = findViewById(R.id.view_profile_tv_jjim);
        tv_jjim.setOnClickListener(new View.OnClickListener() { // 찜한 게시글
            @Override
            public void onClick(View view) {
                getTitle("찜한 게시글");
            }
        });

        tv_selling = findViewById(R.id.view_profile_tv_selling);
        tv_selling.setOnClickListener(new View.OnClickListener() { // 판매 상품
            @Override
            public void onClick(View view) {
                getTitle("판매 상품");
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
        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trySendHeart(jwt, userRealId);
            }
        });

        btn_unfollow = findViewById(R.id.view_profile_btn_unfollow);
        btn_unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryUnsendHeart(jwt, userRealId);
            }
        });

        btn_close = findViewById(R.id.view_profile_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getTitle(String title) {
        SharedPreferences sharedPreferences = getSharedPreferences("categoryName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        System.out.println("String  " + title);
        editor.putString("categoryName", title);
        editor.apply();
        forName = true;

        Intent intent = new Intent(getApplicationContext(), ViewProductActivity.class);
        startActivity(intent);
    }

    private void trySendHeart(Long jwt, String targetUserId) {
        final HeartService heartService = new HeartService(this);
        heartService.sendingHeart(jwt, targetUserId);
        System.out.println("try Send Heart");
    }

    private void tryUnsendHeart(Long jwt, String targetUserId) {
        final HeartService heartService = new HeartService(this);
        heartService.unsendingHeart(jwt, targetUserId);
        System.out.println("try Unsend Heart");
    }

    @Override
    public void sendHeartSuccess(String text) {
        System.out.println("sending Heart Success");
    }

    @Override
    public void sendHeartFailure(String message) {
        System.out.println("sending Heart Failure");
    }

    @Override
    public void unsendHeartSuccess(String text) {
        System.out.println("unsending Heart Success");
    }

    @Override
    public void unsendHeartFailure(String message) {
        System.out.println("unsending Heart Failure");
    }
}