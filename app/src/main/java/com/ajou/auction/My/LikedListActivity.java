package com.ajou.auction.My;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ajou.auction.Category.CategoryAdapter;
import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Category.Model.BettingInfos;
import com.ajou.auction.Category.Service.DeleteMyBoardService;
import com.ajou.auction.Category.ViewPostActivity;
import com.ajou.auction.Category.ViewPostListItem;
import com.ajou.auction.R;

import java.util.ArrayList;
import java.util.List;

import static com.ajou.auction.ApplicationClass.jwt;

public class LikedListActivity extends AppCompatActivity implements GetMyLikeBoardView, DeleteMyBoardView {

    private Button btn_close;
    private ArrayList<ViewMyLikeListItem> dataList = new ArrayList<>();
    private String title;
    private String maxPrice;
    private String image;
    private String deadline;
    private String likecnt;
    private String content;
    private String boardId;
    private String category;
    private String completion;
    private String writerId;
    private String startPrice;
    private String writerNickName;
    private List<BettingInfos> totalbetter;
    LikedListAdapter likedListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_list);

        SwipeRefreshLayout likedlist_swipelayout = findViewById(R.id.likedlist_swipelayout);
        likedlist_swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(LikedListActivity.this, "새로고침", Toast.LENGTH_LONG).show();
                likedListAdapter.clearData();
                getRecyclerView();
                likedlist_swipelayout.setRefreshing(false);
            }
        });

        btn_close = findViewById(R.id.likedList_btn_close);
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
        new GetMyLikeBoardService(LikedListActivity.this).getMyLike(jwt);
        RecyclerView recyclerView = findViewById(R.id.likedList_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        likedListAdapter = new LikedListAdapter(dataList);
        likedListAdapter.clearData();
        recyclerView.setAdapter(likedListAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        likedListAdapter.setOnBoardClicklistener(new OnLikeBoardItemClickListener() {
            @Override
            public void onBoardClick(LikedListAdapter.ViewHolder holder, View view, int position) {
                ViewMyLikeListItem item = likedListAdapter.getItem(position);
                send2ViewPost("LikedListActivity",item.getAuctionDeadline(),item.getBoardId(),item.getCategory(),item.getCompletion(),
                        item.getContent(),item.getLikeNumber(),item.getMaxBettingPrice(),item.getS3imageURL(),item.getStartPrice(),item.getTitle(),item.getWriterId(),
                        item.getWriterNickName(),item.getBettingInfos().size());
            }
        });
    }

    @Override
    public void getMyLikeSuccess(GetMyLikeBoardResponse response) {
        ArrayList<GetMyLikeBoardListInfos> likeBoardListInfos = (ArrayList<GetMyLikeBoardListInfos>)response.getBoardList();
        if (response != null){
            for (GetMyLikeBoardListInfos likeBoardListInfos1 : likeBoardListInfos){
                if (likeBoardListInfos1.getCompletion().equals("N")){
                    title = likeBoardListInfos1.getTitle();
                    maxPrice = likeBoardListInfos1.getMaxBettingPrice().toString();
                    image = likeBoardListInfos1.getS3imageURL();
                    deadline = likeBoardListInfos1.getAuctionDeadline();
                    likecnt = likeBoardListInfos1.getLikeNumber().toString();
                    content = likeBoardListInfos1.getContent();
                    boardId = likeBoardListInfos1.getBoardId().toString();
                    category = likeBoardListInfos1.getCategory().toString();
                    completion = likeBoardListInfos1.getCompletion();
                    startPrice = likeBoardListInfos1.getStartPrice().toString();
                    writerId = likeBoardListInfos1.getWriterId();
                    writerNickName = likeBoardListInfos1.getWriterNickName();
                    totalbetter = likeBoardListInfos1.getBettingInfos();

                    if (likeBoardListInfos1.getCompletion().equals("N")){
                        dataList.add(new ViewMyLikeListItem(
                                deadline,boardId,category,completion,content,likecnt,maxPrice,image,startPrice,title,writerId,writerNickName,totalbetter
                        ));
                    }else {
                        Log.d("likelist", "낙찰된 경매 삭제");
                        new DeleteMyBoardService(LikedListActivity.this).deleteMyBoard(jwt,Long.valueOf(boardId));
                    }
                }
            }
        }else {
            Log.d("getlike","null");
        }
        likedListAdapter.notifyDataSetChanged();
    }

    private void send2ViewPost(String from,String auctionDeadline, String boardID, String category, String completion, String content, String likeNumber, String maxBettingPrice, String s3URL, String startPrice, String title, String writerId, String writerNickName, int totalbetter){
        Intent intent = new Intent(LikedListActivity.this, ViewPostActivity.class);
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
        intent.putExtra("totalbetter", totalbetter);
        startActivity(intent);
        //finish();
    }

    @Override
    public void getMyLikeFailure() {

    }

    @Override
    public void deleteMyBoardSuccess() {

    }

    @Override
    public void deleteMyBoardFailure() {

    }
}