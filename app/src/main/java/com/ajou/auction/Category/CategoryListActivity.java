package com.ajou.auction.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.ajou.auction.R;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {

    private int categoryId;
    private ArrayList<CategoryListItem> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        SharedPreferences sharedPreferences = getSharedPreferences("categoryId", MODE_PRIVATE);
        categoryId = sharedPreferences.getInt("categoryId", categoryId);
        System.out.println("Category Id 확인 " + categoryId);


        for (int i = 0; i < 10; i++) {
            dataList.add(new CategoryListItem("", "제목입니당", "2021-06-11 23:59", "30000", "5"));
        }

        RecyclerView recyclerView = findViewById(R.id.category_list_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        CategoryAdapter categoryAdapter = new CategoryAdapter(dataList);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}