package com.ajou.auction.Login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ajou.auction.Login.Interfaces.SignUpActivityView;
import com.ajou.auction.Login.Services.SignUpService;
import com.ajou.auction.MainActivity;
import com.ajou.auction.R;

public class SignUpActivity extends AppCompatActivity implements SignUpActivityView {
    EditText signUP_edt_id,signUP_edt_password,signUP_edt_pwcheck,signUP_edt_nickname;
    Button signU_btn_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        signU_btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SignUpService(SignUpActivity.this).postSignUp(
                        signUP_edt_id.getText().toString(),
                        signUP_edt_password.getText().toString(),
                        signUP_edt_nickname.getText().toString()
                );
                Toast.makeText(SignUpActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(SignUpActivity.this)
                .setMessage("뒤로가면 내용이 저장되지 않습니다.")
                .setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    public void init(){
        signUP_edt_id = (EditText)findViewById(R.id.signUP_edt_id);
        signUP_edt_password = (EditText)findViewById(R.id.signUP_edt_password);
        signUP_edt_pwcheck = (EditText)findViewById(R.id.signUP_edt_pwcheck);
        signUP_edt_nickname = (EditText)findViewById(R.id.signUP_edt_nickname);
        signU_btn_done = (Button)findViewById(R.id.signU_btn_done);
    }
    @Override
    public void validateSuccess() {
        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateFailure() {
        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_LONG).show();
    }
}