package com.ajou.auction.Category;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ajou.auction.Category.Interface.BettingActivityView;
import com.ajou.auction.Category.Service.BettingService;
import com.ajou.auction.R;

import static com.ajou.auction.ApplicationClass.jwt;

public class ParticipateActivity extends AppCompatActivity implements BettingActivityView {

    private ImageButton btn_close;
    private Button btn_ok;
    private TextView tv_bestPrice;
    private EditText et_price;
    private Long bestPrice;
    private String boardId,myjwt,maxBettingPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate);

        init();
        Intent intent = getIntent();
        boardId = intent.getStringExtra("boardId");
        myjwt = intent.getStringExtra("jwt");
        maxBettingPrice = intent.getStringExtra("maxBettingPrice");


//        SharedPreferences sharedPreferences = getSharedPreferences("boardInfo", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        bestPrice = sharedPreferences.getLong("bestPrice", 1);
//        System.out.println("BestPrice 확인 " + bestPrice);

        tv_bestPrice.setText(maxBettingPrice);


        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ParticipateActivity.this)
                        .setMessage("수정을 중지하고 뒤로 돌아가시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(ParticipateActivity.this, CategoryListActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputPrice = et_price.getText().toString();

                if (inputPrice.isEmpty()) {
                    new AlertDialog.Builder(ParticipateActivity.this)
                            .setMessage("금액을 입력해주세요.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                } else if (Long.parseLong(inputPrice) < Long.parseLong(maxBettingPrice)) {
                    new AlertDialog.Builder(ParticipateActivity.this)
                            .setMessage("최고가보다 높은 금액을 입력해주세요.")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                } else {
                    new AlertDialog.Builder(ParticipateActivity.this)
                            .setMessage("경매에 참여하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    //배팅참여 APi
                                    new BettingService(ParticipateActivity.this).betting(Long.parseLong(inputPrice),Long.parseLong(boardId),jwt);
//                                    Intent intent = new Intent(getApplicationContext(), CategoryListActivity.class);
//                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }

            }
        });
    }

    public void init(){
        et_price = findViewById(R.id.participate_tv_price);
        tv_bestPrice = findViewById(R.id.participate_tv_best_price);
        btn_close = findViewById(R.id.participate_btn_close);
        btn_ok = findViewById(R.id.participate_btn_complete);
    }

    @Override
    public void bettingSuccess() {
        Toast.makeText(ParticipateActivity.this, "베팅하였습니다!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void bettingFailure() {
        Toast.makeText(ParticipateActivity.this, "베팅에 실패하였습니다!", Toast.LENGTH_SHORT).show();
    }
}