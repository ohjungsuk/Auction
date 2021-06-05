package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajou.auction.R;

import java.util.ArrayList;

public class ProfileReviewActivity extends AppCompatActivity {

    private ArrayList<ProfileReviewItem> dataList = new ArrayList<>();
    private Button btn_write;

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


        for (int i = 0; i < 10; i++) {
            dataList.add(new ProfileReviewItem("", "뀨", "너무 좋았어염"));
        }


        RecyclerView recyclerView = findViewById(R.id.profile_review_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ProfileReviewAdapter profileReviewAdapter = new ProfileReviewAdapter(dataList);
        recyclerView.setAdapter(profileReviewAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}