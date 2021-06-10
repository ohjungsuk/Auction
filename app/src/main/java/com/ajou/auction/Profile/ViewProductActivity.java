package com.ajou.auction.Profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ajou.auction.Category.CategoryAdapter2;
import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Category.Service.DeleteMyBoardService;
import com.ajou.auction.My.LikedListActivity;
import com.ajou.auction.My.ViewMyLikeListItem;
import com.ajou.auction.Profile.Interfaces.ProfileViewActivityView;
import com.ajou.auction.Profile.Models.BoardInfo;
import com.ajou.auction.Profile.Models.FollowerInfoList;
import com.ajou.auction.Profile.Models.ProfileViewResponse;
import com.ajou.auction.Profile.Models.ReplyList;
import com.ajou.auction.Profile.Services.ProfileViewService;
import com.ajou.auction.R;

import java.util.ArrayList;

import static com.ajou.auction.ApplicationClass.jwt;

public class ViewProductActivity extends AppCompatActivity implements ProfileViewActivityView, DeleteMyBoardView {

    private ArrayList<BoardInfo> productList = new ArrayList<>();
    private CategoryAdapter2 categoryAdapter2;
    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.view_list_swipelayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ViewProductActivity.this, "새로고침", Toast.LENGTH_LONG).show();
                categoryAdapter2.clearData();
                getRecyclerView();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        btn_close = findViewById(R.id.view_product_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        getRecyclerView();
    }

    private void getRecyclerView(){

        SharedPreferences sharedPreferences = getSharedPreferences("UserId", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userRealId", "");
        System.out.println("현재 프로필을 보려고 하는 User id " + userId);
        tryViewUserProduct(userId);

        RecyclerView recyclerView = findViewById(R.id.view_product_list_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        categoryAdapter2 = new CategoryAdapter2(productList);
        categoryAdapter2.clearData();
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
        for (BoardInfo boardInfo: boardList){
            productList.clear();
            String completion = boardInfo.getCompletion();
            Long boardId = boardInfo.getBoardId();
            if (completion.equals("N")){
                productList.addAll(boardList);
                Log.d("product","test");
            }else {
                Log.d("aaa", "낙찰된 경매 삭제");
                new DeleteMyBoardService(ViewProductActivity.this).deleteMyBoard(jwt,Long.valueOf(boardId));
            }
        }

        categoryAdapter2.notifyDataSetChanged();
        System.out.println("View Product Success");
    }

    @Override
    public void viewProductFailure(String message) {
        System.out.println("View Product Failure");
    }

    @Override
    public void deleteMyBoardSuccess() {

    }

    @Override
    public void deleteMyBoardFailure() {

    }
}