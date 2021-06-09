package com.ajou.auction.Category;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ajou.auction.Category.Interface.UpdateMyBoardView;
import com.ajou.auction.Category.Service.DeleteMyBoardService;
import com.ajou.auction.Category.Service.UpdateMyBoardService;
import com.ajou.auction.R;

import static com.ajou.auction.ApplicationClass.jwt;

public class UpdateMyBoardActivity extends AppCompatActivity implements UpdateMyBoardView {
    private EditText update_edt_content;
    ImageButton update_btn_close;
    Button update_btn_complete;
    private String mContent = null;
    private String mBoardId = null;
    private String mJwt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_my_board);


        update_edt_content = (EditText)findViewById(R.id.update_edt_content);
        Intent intent = getIntent();
        mContent = intent.getStringExtra("content");
        mBoardId = intent.getStringExtra("boardId");
        mJwt = intent.getStringExtra("jwt");
        update_edt_content.setText(mContent);

        update_btn_close = (ImageButton)findViewById(R.id.update_btn_close);
        update_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(UpdateMyBoardActivity.this)
                        .setMessage("수정을 중지하고 뒤로 돌아가시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(UpdateMyBoardActivity.this, CategoryListActivity.class);
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

        update_btn_complete= (Button)findViewById(R.id.update_btn_complete);
        update_btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(UpdateMyBoardActivity.this)
                        .setMessage("게시물을 수정해서 올리시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                //게시물 수정 API호출
                                String fContent = update_edt_content.getText().toString();
                                new UpdateMyBoardService(UpdateMyBoardActivity.this).updateMyBoard(Long.valueOf(mBoardId),fContent,Long.valueOf(mJwt));
//                                Intent intent = new Intent(UpdateMyBoardActivity.this, CategoryListActivity.class);
//                                startActivity(intent);
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
    }

    @Override
    public void updateMyBoardSuccess() {
        Toast.makeText(UpdateMyBoardActivity.this, "게시물 수정 완료", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateMyBoardFailure() {
        Toast.makeText(UpdateMyBoardActivity.this, "게시물 수정 실패", Toast.LENGTH_SHORT).show();
    }

}