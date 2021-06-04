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

import com.ajou.auction.R;

public class ParticipateActivity extends AppCompatActivity {

    private ImageButton btn_close;
    private Button btn_ok;
    private TextView tv_bestPrice;
    private EditText et_price;
    private Long bestPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate);

        SharedPreferences sharedPreferences = getSharedPreferences("boardInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        bestPrice = sharedPreferences.getLong("bestPrice", 1);
        System.out.println("BestPrice 확인 " + bestPrice);

        tv_bestPrice = findViewById(R.id.participate_tv_best_price);
        tv_bestPrice.setText(bestPrice.toString());

        btn_close = findViewById(R.id.participate_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        et_price = findViewById(R.id.participate_tv_price);

        btn_ok = findViewById(R.id.participate_btn_complete);
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
                } else if (Long.parseLong(inputPrice) < bestPrice) {
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
                                    Intent intent = new Intent(getApplicationContext(), ViewPostActivity.class);
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

            }
        });


    }
}