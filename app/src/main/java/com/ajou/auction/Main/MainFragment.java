package com.ajou.auction.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Category.Interface.GetAllBoardView;
import com.ajou.auction.Category.Interface.GetMyBettingView;
import com.ajou.auction.Category.Model.BettingInfos;
import com.ajou.auction.Category.Model.BoardListInfos;
import com.ajou.auction.Category.Model.GetAllBoardResponse;
import com.ajou.auction.Category.Model.GetMyBettingResponse;
import com.ajou.auction.Category.Model.MyBettingBoardListInfos;
import com.ajou.auction.Category.Model.MyBettingListInfos;
import com.ajou.auction.Category.Service.DeleteMyBoardService;
import com.ajou.auction.Category.Service.GetAllBoardService;
import com.ajou.auction.Category.Service.GetMyBettingService;
import com.ajou.auction.Category.ViewPostActivity;
import com.ajou.auction.Category.ViewPostListItem;
import com.ajou.auction.Profile.FollowerItem;
import com.ajou.auction.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.ajou.auction.ApplicationClass.jwt;

public class MainFragment extends Fragment implements GetMyBettingView, DeleteMyBoardView, GetAllBoardView {

    private EditText et_search;
    private TextView tv_popular_title;
    private Button btn_popular, btn_search;
    private ImageView img_gif;
    private String keyWord = null; // 검색 문자열
    private ArrayList<MyBettingListItem> dataList = new ArrayList<>();

    private ArrayList<GetSize> getSizeArrayList = new ArrayList<>();


    private TextView tv_title, hot, tv_price, tv_totalbetter;
    private ImageView img;

    private int max_betteer = 0;

    private String priceOfThisUserBetted = null;
    private String title = null;
    private String auctionDeadline = null;
    private List<BettingInfos> totalbetter = null;
    private String boardId = null;
    private String category = null;
    private String completion = null;
    private String content = null;
    private String likeNumber = null;
    private String maxBettingPrice = null;
    private String s3imageURL = null;
    private String startPrice = null;
    private String writerId = null;
    private String writerNickName = null;

    private String title1 = null;
    private String maxPrice1 = null;
    private String image1 = null;
    private String deadline1 = null;
    private String likecnt1 = null;
    private String content1 = null;
    private String bettedPrice1 = null;
    private String nickName1 = null;
    private String userId1 = null;
    private String boardId1 =null;
    private String category1 = null;
    private String completion1 = null;
    private String startPrice1 = null;
    private String writerId1 = null;
    private String writerNickName1 = null;
    private String myjwt1 = null;
    private Boolean currentUserLikeThisBoard1 = false;
    private List<BettingInfos> totalbetter1=null;

    MyBettingAdapter myBettingAdapter;
    HotBoardAdapter hotBoardAdapter;
    LinearLayout hot_linear;

    private Button mf_btnToHot;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        et_search = view.findViewById(R.id.main_et_search);


        img = view.findViewById(R.id.hotboard_img);
        tv_title = view.findViewById(R.id.hotboard_title);
        tv_price = view.findViewById(R.id.hotboard_price);
        tv_totalbetter = view.findViewById(R.id.hotboard_totalbetter);
        mf_btnToHot = view.findViewById(R.id.mf_btnToHot);
        hot = view.findViewById(R.id.hot);
        hot_linear = view.findViewById(R.id.hot_linear);

        btn_search = view.findViewById(R.id.main_btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyWord = et_search.getText().toString();
                System.out.println("검색 단어 : " + keyWord);

                // keyword에 해당하는 단어를 포함해서 서버로

//                Intent intent = new Intent(getActivity(), SearchListActivity.class);
//                startActivity(intent);
            }
        });

        mf_btnToHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send2ViewPost2("CategoryActivity",deadline1,boardId1,category1,completion1,content1,likecnt1,maxPrice1,image1,startPrice1,title1,writerId1,writerNickName1,
                        myjwt1,currentUserLikeThisBoard1,totalbetter1.size());
            }
        });

        img_gif = view.findViewById(R.id.menu1_gif);
        Glide.with(view).load(R.raw.ads).into(img_gif);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getRecyclerView();
    }

    private void getRecyclerView(){
        new GetMyBettingService(this).getMybetting(jwt);

        RecyclerView recyclerView = getActivity().findViewById(R.id.main_recyclerview_mybet);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        myBettingAdapter = new MyBettingAdapter(dataList);
        myBettingAdapter.clearData();
        recyclerView.setAdapter(myBettingAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        myBettingAdapter.setOnMyBettingClick(new OnMyBettingItemClickListener() {
            @Override
            public void onMyBettingClick(MyBettingAdapter.ViewHolder holder, View view, int position) {
                MyBettingListItem item = myBettingAdapter.getItem(position);
                send2ViewPost("MainFragment",item.getPriceOfThisUserBetted(),item.getAuctionDeadline(),item.getBoardId(),item.getCategory(),item.getCompletion(),item.getContent(),item.getLikeNumber(),item.getMaxBettingPrice(),item.getS3imageURL(),item.getStartPrice(),item.getTitle(),item.getWriterId(),item.getWriterNickName(),item.getBettingInfos().size());
            }
        });

        new GetAllBoardService(this).getAllBoard(jwt);
    }

    @Override
    public void getMyBettingSuccess(GetMyBettingResponse response) {
        MyBettingListInfos myBettingListInfos = new MyBettingListInfos();
        ArrayList<MyBettingListInfos> myBettingListInfosArrayList = (ArrayList<MyBettingListInfos>)response.getBettingInfos();
        MyBettingBoardListInfos myBettingBoardListInfos = myBettingListInfos.getBoardOfThisUserBetted();

        if(response!= null){
            Toast.makeText(getContext(), "내가 배팅한 게시물 조회 성공", Toast.LENGTH_SHORT).show();
            for (MyBettingListInfos list : myBettingListInfosArrayList){
                MyBettingBoardListInfos myBettingBoardListInfos1 = list.getBoardOfThisUserBetted();
                priceOfThisUserBetted = list.getPriceOfThisUserBetted().toString();
                //Log.d("getboard",priceOfThisUserBetted);
                title = myBettingBoardListInfos1.getTitle();
                //Log.d("getboard",title);
                auctionDeadline = myBettingBoardListInfos1.getAuctionDeadline();
                boardId = myBettingBoardListInfos1.getBoardId().toString();
                category = myBettingBoardListInfos1.getCategory().toString();
                completion = myBettingBoardListInfos1.getCompletion();
                content = myBettingBoardListInfos1.getContent();
                likeNumber = myBettingBoardListInfos1.getLikeNumber().toString();
                maxBettingPrice = myBettingBoardListInfos1.getMaxBettingPrice().toString();
                s3imageURL = myBettingBoardListInfos1.getS3imageURL();
                startPrice =  myBettingBoardListInfos1.getStartPrice().toString();
                writerId = myBettingBoardListInfos1.getWriterId();
                writerNickName = myBettingBoardListInfos1.getWriterNickName();
                totalbetter = myBettingBoardListInfos1.getBettingInfos();

                if (myBettingBoardListInfos1.getCompletion().equals("N")){
                    dataList.add(new MyBettingListItem(
                            auctionDeadline,totalbetter ,boardId,category,completion,content,likeNumber,maxBettingPrice,s3imageURL,startPrice,title,priceOfThisUserBetted,writerId,writerNickName
                    ));
                }else {
                    Log.d("mainfragment", "낙찰된 경매 삭제");
                    new DeleteMyBoardService(this).deleteMyBoard(jwt,Long.valueOf(boardId));
                }
            }
        }
        myBettingAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMyBettingFailure() {
        Toast.makeText(getContext(), "내가 배팅한 게시물 조회 실패", Toast.LENGTH_SHORT).show();
    }

    private void send2ViewPost(String title){
        Intent intent = new Intent(getActivity(), ViewPostActivity.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }
    private void send2ViewPost(String from,String priceOfThisUserBetted,String auctionDeadline, String boardID, String category, String completion, String content, String likeNumber, String maxBettingPrice, String s3URL, String startPrice, String title, String writerId, String writerNickName, int totalbetter){
        Intent intent = new Intent(getActivity(), ViewPostActivity.class);
        intent.putExtra("from",from);
        intent.putExtra("priceOfThisUserBetted",priceOfThisUserBetted);
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
    }

    @Override
    public void deleteMyBoardSuccess() {
        Log.d("mainfragment", "완료된 경매 삭제");
    }

    @Override
    public void deleteMyBoardFailure() {

    }

    @Override
    public void getAllBoardSuccess(GetAllBoardResponse response) {
        ArrayList<BoardListInfos> boardListInfos = (ArrayList<BoardListInfos>)response.getBoardList();
        if (response!= null){
            for (BoardListInfos boardListInfos1: boardListInfos){
                if (boardListInfos1.getWriterJwt() != jwt){ // 내 게시물 아닌 것만 띄우기
                    if (boardListInfos1.getCompletion().equals("N")){
                        if (boardListInfos1.getBettingInfos().size() > max_betteer){
                            title1 = boardListInfos1.getTitle();
                            maxPrice1 = boardListInfos1.getMaxBettingPrice().toString();
                            image1 = boardListInfos1.getS3imageURL();
                            deadline1 = boardListInfos1.getAuctionDeadline();
                            likecnt1 = boardListInfos1.getLikeNumber().toString();
                            content1 = boardListInfos1.getContent();
                            boardId1 = boardListInfos1.getBoardId().toString();
                            category1 = boardListInfos1.getCategory().toString();
                            completion1 = boardListInfos1.getCompletion();
                            startPrice1 = boardListInfos1.getStartPrice().toString();
                            writerId1= boardListInfos1.getWriterId();
                            writerNickName1 = boardListInfos1.getWriterNickName();
                            myjwt1 = boardListInfos1.getWriterJwt().toString();
                            currentUserLikeThisBoard1 = boardListInfos1.getCurrentUserLikeThisBoard();
                            totalbetter1 = boardListInfos1.getBettingInfos();

                            max_betteer = boardListInfos1.getBettingInfos().size();
                        }
                    }else {
                        Log.d("mainfragment", "낙찰된 경매 삭제");
                        new DeleteMyBoardService(this).deleteMyBoard(jwt,Long.valueOf(boardId1));
                    }
                    hot_linear.setVisibility(View.VISIBLE);
                }
                else { // 내 게시물이면
                    hot_linear.setVisibility(View.GONE);
                }
            }
            tv_title.setText(title1);
            tv_price.setText(maxPrice1);
            tv_totalbetter.setText(likecnt1);
//            tv_totalbetter.setText(totalbetter1.size());


            Animation mAnimation = new AlphaAnimation(1, 0);
            mAnimation.setDuration(700);
            mAnimation.setInterpolator(new AccelerateInterpolator());
            mAnimation.setRepeatCount(Animation.INFINITE);
            mAnimation.setRepeatMode(Animation.REVERSE);

            tv_price.startAnimation(mAnimation);
            tv_price.setTextColor(Color.RED);
            tv_totalbetter.startAnimation(mAnimation);
            tv_totalbetter.setTextColor(Color.RED);
            hot.startAnimation(mAnimation);
            hot.setTextColor(Color.RED);

            Glide.with(this).load(image1).into(img);
        }
    }

    @Override
    public void getAllBoardFailure() {

    }

    private void send2ViewPost2(String from,String auctionDeadline, String boardID, String category, String completion, String content, String likeNumber, String maxBettingPrice, String s3URL, String startPrice, String title, String writerId, String writerNickName, String myjwt, Boolean currentUserLikeThisBoard, int totalbetter){
        Intent intent = new Intent(getActivity(), ViewPostActivity.class);
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
}