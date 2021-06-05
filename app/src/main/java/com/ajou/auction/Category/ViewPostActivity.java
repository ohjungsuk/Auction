package com.ajou.auction.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajou.auction.Profile.ViewProfileActivity;
import com.ajou.auction.R;

public class ViewPostActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private Button btn_participate, btn_close, btn_modify, btn_delete;
    private ImageView img_btn_like;
    private TextView tv_best_price, tv_id;
    private Long boardId, bestPrice;
    private boolean likeStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        btn_participate = findViewById(R.id.view_post_btn_participate);
        btn_participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("boardInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                boardId = sharedPreferences.getLong("boardId", 0);
                System.out.println("Board Id 확인 " + boardId);

                sharedPreferences = getSharedPreferences("boardInfo", Context.MODE_PRIVATE);
                bestPrice = Long.parseLong(tv_best_price.getText().toString());
                System.out.println("Best Price " + bestPrice);
                editor.putLong("bestPrice", bestPrice);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), ParticipateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_close = findViewById(R.id.view_post_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_best_price = findViewById(R.id.view_post_tv_best_price);

        img_btn_like = findViewById(R.id.view_post_img_btn_like);
        img_btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!likeStatus) { // 하트 눌렀을 경우
                    img_btn_like.setImageResource(R.drawable.img_heart);
                    likeStatus = true;
                } else { // 하트 취소했을 경우
                    img_btn_like.setImageResource(R.drawable.img_heart_origin);
                    likeStatus = false;
                }
            }
        });

        tv_id = findViewById(R.id.view_post_tv_id);

        relativeLayout = findViewById(R.id.view_post_profileLayout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String id = tv_id.getText().toString();
                editor.putString("userId", id);
                editor.apply();
                System.out.println("User Id 확인 " + id);

                Intent intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
                startActivity(intent);
            }
        });

        btn_modify = findViewById(R.id.view_post_btn_modify);

        btn_delete = findViewById(R.id.view_post_btn_delete);
    }
}