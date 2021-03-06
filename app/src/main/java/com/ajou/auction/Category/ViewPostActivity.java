package com.ajou.auction.Category;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajou.auction.Category.Interface.BettingCompleteView;
import com.ajou.auction.Category.Interface.DeleteMyBoardView;
import com.ajou.auction.Category.Interface.LikeAddBoardView;
import com.ajou.auction.Category.Interface.LikeSubBoardView;
import com.ajou.auction.Category.Interface.UpdateMyBoardView;
import com.ajou.auction.Category.Model.BettingCompleteResponse;
import com.ajou.auction.Category.Model.LikeAddBoardResponse;
import com.ajou.auction.Category.Model.LikeSubBoardResponse;
import com.ajou.auction.Category.Service.BettingCompleteService;
import com.ajou.auction.Category.Service.DeleteMyBoardService;
import com.ajou.auction.Category.Service.GetAllBoardService;
import com.ajou.auction.Category.Service.LikeAddBoardService;
import com.ajou.auction.Category.Service.LikeSubBoardService;
import com.ajou.auction.Chat.ChatItem;
import com.ajou.auction.Login.LoginActivity;
import com.ajou.auction.Login.SignUpActivity;
import com.ajou.auction.Main.CancelBettingService;
import com.ajou.auction.Main.CancelBettingView;
import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Profile.ViewProfileActivity;
import com.ajou.auction.R;
import com.bumptech.glide.Glide;

import static com.ajou.auction.ApplicationClass.jwt;

public class ViewPostActivity extends AppCompatActivity implements DeleteMyBoardView, LikeAddBoardView, LikeSubBoardView , CancelBettingView, BettingCompleteView {

    private RelativeLayout relativeLayout;
    private Button btn_participate,btn_end, btn_close, btn_modify, btn_delete,view_post_btn_cancelBetting;
    private ImageView img_btn_like,view_post_img_product;
    private TextView tv_best_price, tv_id,tv_title, tv_category,tv_date,tv_content,tv_start_price,view_post_tv_totalbetter;
    private String boardId,completion,content,likeNumber,maxBettingPrice,s3URL;
    private String category;
    private String deadline,startPrice,title,writerId,writerNickName,myjwt;
    private String totalbetter,WhereFrom;
    private String priceOfThisUserBetted;
    private Long mtotalbetter;
    Intent intent;
    private boolean likeStatus=false;
    private Long updatedLikeNumber;
    private boolean currentUserLikeThisBoard;
    private int checkMyBoard = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        init();
        Intent intent = getIntent();
        WhereFrom = intent.getStringExtra("from");

        if (WhereFrom.equals("CategoryActivity")){
            getCLAData();
            setCLAData();
            if (myjwt.equals(jwt.toString())){
                //??????????????? ??????
                checkMyBoard = 1;
                Log.d("viewPost","????????????");
                btn_participate.setVisibility(View.GONE);
                btn_end.setVisibility(View.VISIBLE);
                view_post_btn_cancelBetting.setVisibility(View.GONE);
                btn_delete.setVisibility(View.VISIBLE);
                btn_modify.setVisibility(View.VISIBLE);
            }
            if (!currentUserLikeThisBoard){
                likeStatus = false;
                img_btn_like.setImageResource(R.drawable.img_heart_origin);
            }else{
                likeStatus= true;
                img_btn_like.setImageResource(R.drawable.img_heart);
            }
        }else if(WhereFrom.equals("MainFragment")){
            getMFData();
            setMFData();
            btn_participate.setVisibility(View.GONE);
            btn_end.setVisibility(View.GONE);
            view_post_btn_cancelBetting.setVisibility(View.VISIBLE);
        }else { //LikedListActivity
            getLLData();
            setLLData();
            btn_participate.setVisibility(View.VISIBLE);
            btn_end.setVisibility(View.GONE);
            view_post_btn_cancelBetting.setVisibility(View.GONE);
            img_btn_like.setImageResource(R.drawable.img_heart);
            likeStatus = true;
        }
        btnMover();
    }
    public void init(){
        view_post_btn_cancelBetting = findViewById(R.id.view_post_btn_cancelBetting);
        view_post_img_product = findViewById(R.id.view_post_img_product);
        relativeLayout = findViewById(R.id.view_post_profileLayout);
        btn_modify = findViewById(R.id.view_post_btn_modify);
        btn_delete = findViewById(R.id.view_post_btn_delete);
        tv_id = findViewById(R.id.view_post_tv_id);
        tv_title = findViewById(R.id.view_post_tv_title);
        tv_category = findViewById(R.id.view_post_tv_category);
        tv_date = findViewById(R.id.view_post_tv_date);
        tv_content = findViewById(R.id.view_post_tv_content);
        tv_start_price = findViewById(R.id.view_post_tv_start_price_number);
        tv_best_price = findViewById(R.id.view_post_tv_best_price);
        img_btn_like = findViewById(R.id.view_post_img_btn_like);
        btn_close = findViewById(R.id.view_post_btn_close);
        btn_participate = findViewById(R.id.view_post_btn_participate);
        btn_end = findViewById(R.id.view_post_btn_completeMyBetting);
        view_post_tv_totalbetter = findViewById(R.id.view_post_tv_totalbetter);
    }
    public void getLLData(){
        intent = getIntent();
        deadline = intent.getStringExtra("auctionDeadline");
        boardId = intent.getStringExtra("boardID");
        category = intent.getStringExtra("category");
        completion = intent.getStringExtra("completion");
        content = intent.getStringExtra("content");
        likeNumber = intent.getStringExtra("likeNumber");
        maxBettingPrice = intent.getStringExtra("maxBettingPrice");
        s3URL = intent.getStringExtra("s3URL");
        startPrice = intent.getStringExtra("startPrice");
        title = intent.getStringExtra("title");
        Log.d("intent",title);
        writerId = intent.getStringExtra("writerId");
        writerNickName = intent.getStringExtra("writerNickName");
        totalbetter = String.valueOf(intent.getIntExtra("totalbetter",0));
    }

    public void setLLData(){
        tv_id.setText(writerNickName);
        Glide.with(ViewPostActivity.this).load(s3URL).into(view_post_img_product);
        tv_title.setText(title);
        if (Long.parseLong(category) == 1){
            tv_category.setText("??????/??????");
        }else if (Long.parseLong(category) == 2){
            tv_category.setText("??????");
        }else if (Long.parseLong(category) == 3){
            tv_category.setText("?????????/??????");
        }else if (Long.parseLong(category) == 4){
            tv_category.setText("??????/????????????");
        }else if (Long.parseLong(category) == 5){
            tv_category.setText("??????/????????????");
        }else if (Long.parseLong(category) == 6){
            tv_category.setText("?????????/??????");
        }else if (Long.parseLong(category) == 7){
            tv_category.setText("??????/??????");
        }else if (Long.parseLong(category) == 8){
            tv_category.setText("??????/??????/??????");
        }else if (Long.parseLong(category) == 9){
            tv_category.setText("??????/????????????");
        }
        tv_date.setText(deadline);
        tv_content.setText(content);
        tv_best_price.setText(maxBettingPrice);
        tv_start_price.setText(startPrice);
        view_post_tv_totalbetter.setText(totalbetter);
    }

    public void getMFData(){
        intent = getIntent();
        priceOfThisUserBetted = intent.getStringExtra("priceOfThisUserBetted");
        deadline = intent.getStringExtra("auctionDeadline");
        boardId = intent.getStringExtra("boardID");
        category = intent.getStringExtra("category");
        completion = intent.getStringExtra("completion");
        content = intent.getStringExtra("content");
        likeNumber = intent.getStringExtra("likeNumber");
        maxBettingPrice = intent.getStringExtra("maxBettingPrice");
        s3URL = intent.getStringExtra("s3URL");
        startPrice = intent.getStringExtra("startPrice");
        title = intent.getStringExtra("title");
        Log.d("intent",title);
        writerId = intent.getStringExtra("writerId");
        writerNickName = intent.getStringExtra("writerNickName");
        totalbetter = String.valueOf(intent.getIntExtra("totalbetter",0));
    }

    public void setMFData(){
        tv_id.setText(writerNickName);
        Glide.with(ViewPostActivity.this).load(s3URL).into(view_post_img_product);
        tv_title.setText(title);
        if (Long.parseLong(category) == 1){
            tv_category.setText("??????/??????");
        }else if (Long.parseLong(category) == 2){
            tv_category.setText("??????");
        }else if (Long.parseLong(category) == 3){
            tv_category.setText("?????????/??????");
        }else if (Long.parseLong(category) == 4){
            tv_category.setText("??????/????????????");
        }else if (Long.parseLong(category) == 5){
            tv_category.setText("??????/????????????");
        }else if (Long.parseLong(category) == 6){
            tv_category.setText("?????????/??????");
        }else if (Long.parseLong(category) == 7){
            tv_category.setText("??????/??????");
        }else if (Long.parseLong(category) == 8){
            tv_category.setText("??????/??????/??????");
        }else if (Long.parseLong(category) == 9){
            tv_category.setText("??????/????????????");
        }
        tv_date.setText(deadline);
        tv_content.setText(content);
        tv_best_price.setText(maxBettingPrice);
        tv_start_price.setText(startPrice);
        view_post_tv_totalbetter.setText(totalbetter);
    }

    public void setCLAData(){
        tv_id.setText(writerNickName);
        Glide.with(ViewPostActivity.this).load(s3URL).into(view_post_img_product);
        tv_title.setText(title);
        if (Long.parseLong(category) == 1){
            tv_category.setText("??????/??????");
        }else if (Long.parseLong(category) == 2){
            tv_category.setText("??????");
        }else if (Long.parseLong(category) == 3){
            tv_category.setText("?????????/??????");
        }else if (Long.parseLong(category) == 4){
            tv_category.setText("??????/????????????");
        }else if (Long.parseLong(category) == 5){
            tv_category.setText("??????/????????????");
        }else if (Long.parseLong(category) == 6){
            tv_category.setText("?????????/??????");
        }else if (Long.parseLong(category) == 7){
            tv_category.setText("??????/??????");
        }else if (Long.parseLong(category) == 8){
            tv_category.setText("??????/??????/??????");
        }else if (Long.parseLong(category) == 9){
            tv_category.setText("??????/????????????");
        }
        tv_date.setText(deadline);
        tv_content.setText(content);
        tv_best_price.setText(maxBettingPrice);
        tv_start_price.setText(startPrice);
        view_post_tv_totalbetter.setText(totalbetter);
    }

    public void getCLAData(){
        intent = getIntent();
        deadline = intent.getStringExtra("auctionDeadline");
        boardId = intent.getStringExtra("boardID");
        category = intent.getStringExtra("category");
        completion = intent.getStringExtra("completion");
        content = intent.getStringExtra("content");
        likeNumber = intent.getStringExtra("likeNumber");
        maxBettingPrice = intent.getStringExtra("maxBettingPrice");
        s3URL = intent.getStringExtra("s3URL");
        startPrice = intent.getStringExtra("startPrice");
        title = intent.getStringExtra("title");
        Log.d("intent",title);
        writerId = intent.getStringExtra("writerId");
        writerNickName = intent.getStringExtra("writerNickName");
        myjwt = intent.getStringExtra("myjwt");
        currentUserLikeThisBoard = intent.getBooleanExtra("currentUserLikeThisBoard",false);
        totalbetter = String.valueOf(intent.getIntExtra("totalbetter",0));

    }

    public void btnMover(){
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Long.parseLong(totalbetter) >0){
                    new AlertDialog.Builder(ViewPostActivity.this)
                            .setMessage("?????? ?????? ???????????? ????????? ????????? ?????????????????????????")
                            .setPositiveButton("???", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    //?????? API ??????
                                    new BettingCompleteService(ViewPostActivity.this).completeBetting(jwt,Long.valueOf(boardId));
                                    //finish();
                                }
                            })
                            .setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }else {
                    new AlertDialog.Builder(ViewPostActivity.this)
                            .setMessage("??? ????????? ????????? ????????? ????????????. ????????? ????????? ????????????????")
                            .setPositiveButton("???", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    //?????? API ??????
                                    new BettingCompleteService(ViewPostActivity.this).completeBetting(jwt,Long.valueOf(boardId));
                                    finish();
                                }
                            })
                            .setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }
            }
        });

        view_post_btn_cancelBetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //?????? ???????????? ?????? API
                new CancelBettingService(ViewPostActivity.this).cancelbetting(jwt,Long.valueOf(boardId));
                finish();
            }
        });


        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPostActivity.this,UpdateMyBoardActivity.class);
                intent.putExtra("content",content);
                intent.putExtra("boardId",boardId);
                intent.putExtra("jwt",myjwt);
                startActivity(intent);
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ViewPostActivity.this)
                        .setMessage("?????? ?????? ???????????? ????????? ?????????????????????????")
                        .setPositiveButton("???", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                //?????? API ??????
                                new DeleteMyBoardService(ViewPostActivity.this).deleteMyBoard(jwt,Long.valueOf(boardId));
//                                Intent intent = new Intent(ViewPostActivity.this, CategoryListActivity.class);
//                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });

        btn_participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ParticipateActivity.class);
                intent.putExtra("boardId",boardId);
                intent.putExtra("jwt",myjwt);
                intent.putExtra("maxBettingPrice",maxBettingPrice);
                startActivity(intent);
                finish();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkMyBoard == 0){
                    if (!likeStatus) { // ?????? ????????? ??????
                        //????????? APi ??????
                        img_btn_like.setImageResource(R.drawable.img_heart);
                        new LikeAddBoardService(ViewPostActivity.this).likeAddBoard(jwt,Long.valueOf(boardId));

                    } else { // ?????? ???????????? ??????
                        //????????? ?????? APi ??????
                        img_btn_like.setImageResource(R.drawable.img_heart_origin);
                        new LikeSubBoardService(ViewPostActivity.this).likeSubBoard(jwt,Long.valueOf(boardId));
                    }
                }else if(checkMyBoard == 1) { //?????????????????????
                    Toast.makeText(ViewPostActivity.this, "??? ???????????? ????????? ?????? ?????????!", Toast.LENGTH_SHORT).show();
                }
//                else if (checkMyBoard ==2){ // ???????????? ???????????? ???????????????
//                    img_btn_like.setImageResource(R.drawable.img_heart);
//                    new LikeSubBoardService(ViewPostActivity.this).likeSubBoard(jwt,Long.valueOf(boardId));
//                }
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String id = tv_id.getText().toString();
                editor.putString("userId", id);
                editor.apply();
                System.out.println("User Id ?????? " + id);

                Intent intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void deleteMyBoardSuccess() {
        Toast.makeText(ViewPostActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteMyBoardFailure() {
        Toast.makeText(ViewPostActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void likeAddBoardSuccess(LikeAddBoardResponse response) {
        if (response != null){
            Toast.makeText(ViewPostActivity.this, "????????????!", Toast.LENGTH_SHORT).show();
            likeStatus = true;
        }else{
            Toast.makeText(ViewPostActivity.this, "null", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void likeAddBoardFailure() {
        Toast.makeText(ViewPostActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void likeSubBoardSuccess(LikeSubBoardResponse response) {
        if (response != null){
            Toast.makeText(ViewPostActivity.this, "????????? ??????!", Toast.LENGTH_SHORT).show();
            likeStatus = false;
        }else{
            Toast.makeText(ViewPostActivity.this, "null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void likeSubBoardFailure() {
        Toast.makeText(ViewPostActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelBettingSuccess() {
        Toast.makeText(ViewPostActivity.this, "?????? ?????? ??????", Toast.LENGTH_LONG).show();
    }

    @Override
    public void cancelBettingFailure() {
        Toast.makeText(ViewPostActivity.this, "?????? ?????? ??????", Toast.LENGTH_LONG).show();

    }

    @Override
    public void completeBettingSuccess(BettingCompleteResponse response) {
        if (response != null){
            Toast.makeText(ViewPostActivity.this, "??? ????????? ?????????????????????!", Toast.LENGTH_SHORT).show();
            Toast.makeText(ViewPostActivity.this, response.getSelectedUserNickName() + " ?????? ???????????? ?????????????????????!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(ViewPostActivity.this,CategoryListActivity.class);
//            intent.putExtra("selectedUserNickName",response.getSelectedUserNickName());
//            startActivity(intent);
            ApplicationClass applicationClass = new ApplicationClass();
            applicationClass.getNickNameForChatting.add(response.getSelectedUserNickName());
            for (int i= 0; i<applicationClass.getNickNameForChatting.size(); i++){
                Log.d("checkchatting",applicationClass.getNickNameForChatting.get(i));
            }
            finish();
        }else {
            Toast.makeText(ViewPostActivity.this, "null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void completeBettingFailure() {
        Toast.makeText(ViewPostActivity.this, "?????? ??????", Toast.LENGTH_LONG).show();
    }
}