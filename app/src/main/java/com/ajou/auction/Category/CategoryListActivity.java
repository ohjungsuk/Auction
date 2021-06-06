package com.ajou.auction.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ajou.auction.Category.Interface.GetAllBoardView;
import com.ajou.auction.Category.Model.BoardListInfos;
import com.ajou.auction.Category.Model.GetAllBoardResponse;
import com.ajou.auction.Category.Service.GetAllBoardService;
import com.ajou.auction.R;

import java.util.ArrayList;

import static com.ajou.auction.ApplicationClass.jwt;
import static com.ajou.auction.Profile.ViewProfileActivity.forName;

public class CategoryListActivity extends AppCompatActivity implements GetAllBoardView {

    private Long categoryId;
    private ArrayList<RecyclerPostListItem> dataList = new ArrayList<>();
    private Button btn_close;
    private TextView tv_category_name;
    private String categoryName = "";

    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.category_list_swipelayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(CategoryListActivity.this, "새로고침", Toast.LENGTH_LONG).show();
                getRecyclerView();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

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

        //getRecyclerView();


//        for (int i = 0; i < 10; i++) {
//            dataList.add(new CategoryListItem("", "제목입니당", "2021-06-11 23:59", "30000", "5"));
//        }

//        RecyclerView recyclerView = findViewById(R.id.category_list_recyclerview);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//
//        CategoryAdapter categoryAdapter = new CategoryAdapter(dataList);
//        recyclerView.setAdapter(categoryAdapter);
//        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRecyclerView();
    }

//    private void addItem(String title,String maxBettingPrice,String s3imageURL,String auctionDeadline,String likeNumber){
//        RecyclerPostListItem item = new RecyclerPostListItem();
//
//        item.setTitle(title);
//        item.setMaxBettingPrice(maxBettingPrice);
//        item.setS3imageURL(s3imageURL);
//        item.setAuctionDeadline(auctionDeadline);
//        item.setLikeNumber(likeNumber);
//        Log.d("getboard","additem");
//    }

    private void getRecyclerView(){
        new GetAllBoardService(CategoryListActivity.this).getAllBoard(jwt);
        ArrayList<RecyclerPostListItem> recyclerPostListItems = new ArrayList<RecyclerPostListItem>();

        RecyclerView recyclerView = findViewById(R.id.category_list_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        categoryAdapter = new CategoryAdapter(dataList);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void getAllBoardSuccess(GetAllBoardResponse response) {

        ArrayList<BoardListInfos> boardListInfos = (ArrayList<BoardListInfos>)response.getBoardList();
        if(response != null){
            String title = null;
            String price = null;
            String image = null;
            String deadline = null;
            String likecnt = null;

            for (BoardListInfos boardListInfo : boardListInfos){
                if(categoryId == boardListInfo.getCategory()){
                    title = boardListInfo.getTitle();
                    price = boardListInfo.getMaxBettingPrice().toString();
                    image = boardListInfo.getS3imageURL();
                    deadline = boardListInfo.getAuctionDeadline();
                    likecnt = boardListInfo.getLikeNumber().toString();

                    //addItem(boardListInfo.getTitle(),boardListInfo.getMaxBettingPrice(),boardListInfo.getS3imageURL(),boardListInfo.getAuctionDeadline(),boardListInfo.getLikeNumber());
                    dataList.add(new RecyclerPostListItem(
                        title,price,image,deadline,likecnt
                    ));
                    Log.d("getboard",title);
                    Log.d("getboard",price);
                    Log.d("getboard",image);
                    Log.d("getboard",deadline);
                    Log.d("getboard",likecnt);
                }
            }
            //Log.d("getboard","good"+title);
        }else{
            Log.d("getboard","null");
        }
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAllBoardFailure() {

    }
}