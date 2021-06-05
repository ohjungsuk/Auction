package com.ajou.auction.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ajou.auction.R;

import java.util.ArrayList;

import static com.ajou.auction.Profile.ViewProfileActivity.forName;

public class CategoryListActivity extends AppCompatActivity {

    private Long categoryId;
    private ArrayList<CategoryListItem> dataList = new ArrayList<>();
    private Button btn_close;
    private TextView tv_category_name;
    private String categoryName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        SharedPreferences sharedPreferences = getSharedPreferences("categoryId", MODE_PRIVATE);
        categoryId = sharedPreferences.getLong("categoryId", 0);
        System.out.println("Category Id 확인 " + categoryId);



        btn_close = findViewById(R.id.category_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (forName) {
            sharedPreferences = getSharedPreferences("categoryName", MODE_PRIVATE);
            String name = sharedPreferences.getString("categoryName", "");
            System.out.println("Category 이름 확인 " + name);
            categoryName = name;
            forName = false;
        } else if (categoryId == 1) {
            categoryName = "의류/잡화";
        } else if (categoryId == 2) {
            categoryName = "뷰티";
        } else if (categoryId == 3) {
            categoryName = "디지털/가전";
        } else if (categoryId == 4) {
            categoryName = "가구/인테리어";
        } else if (categoryId == 5) {
            categoryName = "생활/가공식품";
        } else if (categoryId == 6) {
            categoryName = "스포츠/레저";
        } else if (categoryId == 7) {
            categoryName = "게임/취미";
        } else if (categoryId == 8) {
            categoryName = "도서/티켓/음반";
        } else if (categoryId == 9) {
            categoryName = "기타/무료나눔";
        }

        tv_category_name = findViewById(R.id.category_tv_name);
        tv_category_name.setText(categoryName);


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