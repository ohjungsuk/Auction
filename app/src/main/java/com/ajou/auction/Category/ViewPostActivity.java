package com.ajou.auction.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajou.auction.R;

public class ViewPostActivity extends AppCompatActivity {

    private Button btn_participate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        btn_participate = findViewById(R.id.view_post_btn_participate);
        btn_participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParticipateActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}