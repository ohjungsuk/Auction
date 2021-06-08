package com.ajou.auction.Profile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ajou.auction.Profile.Interfaces.ProfileAddReplyActivityView;
import com.ajou.auction.Profile.Services.ProfileAddReplyService;
import com.ajou.auction.R;
import static com.ajou.auction.ApplicationClass.jwt;

public class WriteReviewActivity extends AppCompatActivity implements ProfileAddReplyActivityView {

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
                SharedPreferences sharedPreferences = getSharedPreferences("UserId", Context.MODE_PRIVATE);
                String userId = sharedPreferences.getString("userRealId", "");
                System.out.println("User Id 확인 (write review) " + userId);
                System.out.println("content (write review) " + et_content.getText().toString());
                System.out.println("content (jwt) " + jwt);
                tryAddReply(et_content.getText().toString(), jwt, userId);

                Intent intent = new Intent(getApplicationContext(), ProfileReviewActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void tryAddReply(String reply_content, Long reply_jwt, String targetUserId) {
        final ProfileAddReplyService profileAddReplyService = new ProfileAddReplyService(this);
        profileAddReplyService.postAddReply(reply_content, reply_jwt, targetUserId);
        System.out.println("try Add Reply");
    }

    @Override
    public void addReplySuccess(String text) {
        Toast.makeText(this,"댓글 등록 성공",Toast.LENGTH_SHORT).show();
        System.out.println("Add reply Success");
    }

    @Override
    public void addReplyFailure(String message) {
        System.out.println("Add reply Failure");
    }
}