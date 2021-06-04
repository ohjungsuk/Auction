package com.ajou.auction.My;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajou.auction.R;

import java.util.ArrayList;

public class LikedListActivity extends AppCompatActivity {

    private Button btn_close;
    private ArrayList<LikedListItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_list);

        btn_close = findViewById(R.id.likedList_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for (int i = 0; i < 10; i++) {
            dataList.add(new LikedListItem("", "제목입니당", "2021-06-11 23:59", "30000", "5"));
        }

        RecyclerView recyclerView = findViewById(R.id.likedList_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        LikedListAdapter likedListAdapter = new LikedListAdapter(dataList);
        recyclerView.setAdapter(likedListAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}