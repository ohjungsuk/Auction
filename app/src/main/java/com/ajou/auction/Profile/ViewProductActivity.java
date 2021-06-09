package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ajou.auction.Category.CategoryAdapter2;
import com.ajou.auction.Profile.Interfaces.ProfileViewActivityView;
import com.ajou.auction.Profile.Models.BoardInfo;
import com.ajou.auction.Profile.Models.FollowerInfoList;
import com.ajou.auction.Profile.Models.ProfileViewResponse;
import com.ajou.auction.Profile.Models.ReplyList;
import com.ajou.auction.Profile.Services.ProfileViewService;
import com.ajou.auction.R;

import java.util.ArrayList;

public class ViewProductActivity extends AppCompatActivity implements ProfileViewActivityView {

    private ArrayList<BoardInfo> productList = new ArrayList<>();
    private CategoryAdapter2 categoryAdapter2;
    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        btn_close = findViewById(R.id.view_product_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("UserId", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userRealId", "");
        System.out.println("현재 프로필을 보려고 하는 User id " + userId);
        tryViewUserProduct(userId);

        RecyclerView recyclerView = findViewById(R.id.view_product_list_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        categoryAdapter2 = new CategoryAdapter2(productList);
        recyclerView.setAdapter(categoryAdapter2);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void tryViewUserProduct(String targetUserId) {
        final ProfileViewService profileViewService = new ProfileViewService(this);
        profileViewService.viewProduct(targetUserId);
        System.out.println("try View Follower");
    }

    @Override
    public void viewProfileSuccess(ArrayList<ReplyList> dataList) {

    }

    @Override
    public void viewProfileFailure(String message) {

    }

    @Override
    public void viewFollowerSuccess(ArrayList<FollowerInfoList> followerList) {

    }

    @Override
    public void viewFollowerFailure(String message) {

    }

    @Override
    public void viewProductSuccess(ArrayList<BoardInfo> boardList, ProfileViewResponse response) {

    }

    @Override
    public void viewProductSuccess2(ArrayList<BoardInfo> boardList) {
        productList.addAll(boardList);

        categoryAdapter2.notifyDataSetChanged();
        System.out.println("View Product Success");
    }

    @Override
    public void viewProductFailure(String message) {
        System.out.println("View Product Failure");
    }
}