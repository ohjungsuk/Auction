package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajou.auction.R;

import java.util.ArrayList;

public class FollowerActivity extends AppCompatActivity {

    private Button btn_close;
    private ArrayList<FollowerItem> dataList = new ArrayList<>();

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

        for (int i = 0; i < 10; i++) {
            dataList.add(new FollowerItem("", "아이디.."));
        }

        RecyclerView recyclerView = findViewById(R.id.follower_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        FollowerAdapter followerAdapter = new FollowerAdapter(dataList);
        recyclerView.setAdapter(followerAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}