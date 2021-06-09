package com.ajou.auction.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Category.Interface.GetAllBoardView;
import com.ajou.auction.Category.Model.BettingInfos;
import com.ajou.auction.Category.Model.BoardListInfos;
import com.ajou.auction.Category.Model.GetAllBoardResponse;
import com.ajou.auction.Category.Service.DeleteMyBoardService;
import com.ajou.auction.Category.Service.GetAllBoardService;
import com.ajou.auction.Main.MyBettingListItem;
import com.ajou.auction.Profile.FollowerAdapter;
import com.ajou.auction.Profile.Interfaces.ProfileViewActivityView;
import com.ajou.auction.Profile.Models.BoardInfo;
import com.ajou.auction.Profile.Models.FollowerInfoList;
import com.ajou.auction.Profile.Models.ProfileViewResponse;
import com.ajou.auction.Profile.Models.ReplyList;
import com.ajou.auction.Profile.Services.ProfileViewService;
import com.ajou.auction.R;

import java.util.ArrayList;
import java.util.List;

import static com.ajou.auction.ApplicationClass.jwt;
import static com.ajou.auction.Profile.ViewProfileActivity.forName;

public class CategoryListActivity extends AppCompatActivity implements GetAllBoardView, ProfileViewActivityView, DeleteMyBoardView {

    private Long categoryId;
    private ArrayList<ViewPostListItem> dataList = new ArrayList<>();
    private Button btn_close;
    private TextView tv_category_name;
    private String categoryName = "";
    private String title = null;
    private String maxPrice = null;
    private String image = null;
    private String deadline = null;
    private String likecnt = null;
    private String content = null;
    private String bettedPrice = null;
    private String nickName = null;
    private String userId = null;
    private String boardId =null;
    private String category = null;
    private String completion = null;
    private String startPrice = null;
    private String writerId = null;
    private String writerNickName = null;
    private String myjwt = null;
    private Boolean currentUserLikeThisBoard = false;
    private List<BettingInfos> totalbetter;
    private Intent intent;

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
                categoryAdapter.clearData();
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
    }

    @Override
    protected void onResume() {
        super.onResume();

        getRecyclerView();
    }

    private void getRecyclerView(){
        new GetAllBoardService(CategoryListActivity.this).getAllBoard(jwt);  //게시물가져오기 API호출
        ArrayList<ViewPostListItem> recyclerPostListItems = new ArrayList<ViewPostListItem>();

        RecyclerView recyclerView = findViewById(R.id.category_list_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        categoryAdapter = new CategoryAdapter(dataList);
        categoryAdapter.clearData();
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        categoryAdapter.setOnBoardClicklistener(new OnBoardItemClickListener() {
            @Override
            public void onBoardClick(CategoryAdapter.ViewHolder holder, View view, int position) {
                ViewPostListItem item = categoryAdapter.getItem(position);
                //ViewPost액티비티로 이동
                send2ViewPost("CategoryActivity",item.getAuctionDeadline(),item.getBoardId(),item.getCategory(),item.getCompletion(),
                        item.getContent(),item.getLikeNumber(),item.getMaxBettingPrice(),item.getS3imageURL(),item.getStartPrice(),item.getTitle(),item.getWriterId(),
                        item.getWriterNickName(),item.getJwt(),item.getCurrentUserLikeThisBoard(),item.getBettingInfos().size());
                //Log.d("viewpost", item.getBoardId());

                SharedPreferences sharedPreferences = getSharedPreferences("UserId", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String userId = item.getWriterId(); // boardId 받아서 저장해줘야함
                System.out.println("Real ID " + userId);
                editor.putString("userRealId", userId);
                editor.apply();
            }
        });
    }

    @Override
    public void getAllBoardSuccess(GetAllBoardResponse response) {

        BoardListInfos boardListInfoss = new BoardListInfos();
        ArrayList<BoardListInfos> boardListInfos = (ArrayList<BoardListInfos>)response.getBoardList();
        ArrayList<BettingInfos> bettingInfos = (ArrayList<BettingInfos>)boardListInfoss.getBettingInfos();
        if(response != null){
            for (BoardListInfos boardListInfo : boardListInfos){
                if(categoryId == boardListInfo.getCategory()){
                    if (boardListInfo.getCompletion().equals("N")){
                        title = boardListInfo.getTitle();
                        maxPrice = boardListInfo.getMaxBettingPrice().toString();
                        image = boardListInfo.getS3imageURL();
                        deadline = boardListInfo.getAuctionDeadline();
                        likecnt = boardListInfo.getLikeNumber().toString();
                        content = boardListInfo.getContent();
                        boardId = boardListInfo.getBoardId().toString();
                        category = boardListInfo.getCategory().toString();
                        completion = boardListInfo.getCompletion();
                        startPrice = boardListInfo.getStartPrice().toString();
                        writerId = boardListInfo.getWriterId();
                        writerNickName = boardListInfo.getWriterNickName();
                        myjwt = boardListInfo.getWriterJwt().toString();
                        currentUserLikeThisBoard = boardListInfo.getCurrentUserLikeThisBoard();
                        totalbetter = boardListInfo.getBettingInfos();

                        if (boardListInfo.getCompletion().equals("N")){
                            dataList.add(new ViewPostListItem(
                                    deadline,boardId,category,completion,content,likecnt,maxPrice,image,startPrice,title,writerId,writerNickName,myjwt,currentUserLikeThisBoard,totalbetter
                            ));
                        }else {
                            Log.d("mainfragment", "낙찰된 경매 삭제");
                            new DeleteMyBoardService(CategoryListActivity.this).deleteMyBoard(jwt,Long.valueOf(boardId));
                        }

                    }
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
                                                 // List<BettingInfos> bettingInfos
    private void send2ViewPost(String from,String auctionDeadline, String boardID, String category, String completion, String content, String likeNumber, String maxBettingPrice, String s3URL, String startPrice, String title, String writerId, String writerNickName, String myjwt, Boolean currentUserLikeThisBoard, int totalbetter){
        Intent intent = new Intent(CategoryListActivity.this, ViewPostActivity.class);
        intent.putExtra("from",from);
        intent.putExtra("auctionDeadline", auctionDeadline);
        intent.putExtra("boardID", boardID);
        intent.putExtra("category", category);
        intent.putExtra("completion", completion);
        intent.putExtra("content", content);
        intent.putExtra("likeNumber", likeNumber);
        intent.putExtra("maxBettingPrice", maxBettingPrice);
        intent.putExtra("s3URL", s3URL);
        intent.putExtra("startPrice", startPrice);
        intent.putExtra("title", title);
        intent.putExtra("writerId", writerId);
        intent.putExtra("writerNickName", writerNickName);
        intent.putExtra("myjwt",myjwt);
        intent.putExtra("currentUserLikeThisBoard", currentUserLikeThisBoard);
        intent.putExtra("totalbetter", totalbetter);
        startActivity(intent);
        //finish();
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

    }


    @Override
    public void viewProductFailure(String message) {
    }

    @Override
    public void deleteMyBoardSuccess() {

    }

    @Override
    public void deleteMyBoardFailure() {

    }
}