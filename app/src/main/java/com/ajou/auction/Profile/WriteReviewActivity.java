package com.ajou.auction.Profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Post.PostActivity;
import com.ajou.auction.R;

public class WriteReviewActivity extends AppCompatActivity {

    private ImageButton btn_close;
    private Button btn_complete;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        et_content = findViewById(R.id.review_write_et_content);

        btn_close = findViewById(R.id.review_write_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(WriteReviewActivity.this)
                        .setMessage("뒤로 가시면 내용이 저장되지 않습니다.")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });

        btn_complete = findViewById(R.id.review_write_btn_complete);
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileReviewActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}