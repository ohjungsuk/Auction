package com.ajou.auction.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ajou.auction.R;

import java.util.ArrayList;

import static com.ajou.auction.Main.MainFragment.keyWord;

public class SearchListActivity extends AppCompatActivity {

    private Button btn_close;
    private TextView tv_search_keyword;
    private ArrayList<SearchListItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        btn_close = findViewById(R.id.searchList_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_search_keyword = findViewById(R.id.searchList_tv_name);
        String input = "\"" + keyWord + "\" 에 대한 검색 결과";
        tv_search_keyword.setText(input);
        System.out.println("input " + input);

        for (int i = 0; i < 10; i++) {
            dataList.add(new SearchListItem("", "제목입니당", "2021-06-11 23:59", "30000", "5"));
        }

        RecyclerView recyclerView = findViewById(R.id.searchList_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        SearchAdapter searchAdapter = new SearchAdapter(dataList);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}