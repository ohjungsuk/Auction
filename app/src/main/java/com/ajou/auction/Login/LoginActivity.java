package com.ajou.auction.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajou.auction.ApplicationClass;
import com.ajou.auction.Login.Interfaces.LogInActivityView;
import com.ajou.auction.Login.Models.LogInResponse;
import com.ajou.auction.Login.Services.LogInService;
import com.ajou.auction.MainActivity;
import com.ajou.auction.R;

import static com.ajou.auction.ApplicationClass.jwt;

public class LoginActivity extends AppCompatActivity implements LogInActivityView {

    private Button btn_login, btn_signup;
    private EditText et_login_id, et_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUserData();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_login_id.getText().toString()!=null && et_login_password.getText().toString()!=null){
                    new LogInService(LoginActivity.this).postLogIn(
                            et_login_id.getText().toString(),
                            et_login_password.getText().toString()
                    );
                }else {
                    Toast.makeText(LoginActivity.this, "회원정보를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveAccessToken(Long jwt){
        SharedPreferences.Editor editor = ApplicationClass.sSharedPreferences.edit();
        editor.putLong("jwt",jwt);
        editor.apply();
    }

    public void setUserData(){
        et_login_id = (EditText) findViewById(R.id.login_et_id);
        et_login_password = (EditText) findViewById(R.id.login_et_password);
        btn_login = (Button)findViewById(R.id.login_btn);
        btn_signup = findViewById(R.id.login_btn_signup);
    }

    @Override
    public void validateFailure() {
        Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateSuccess(LogInResponse response) {
        //saveAccessToken(response.getJwt());
        if(response == null){
            Toast.makeText(LoginActivity.this, "회원정보를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
            //Log.d("logintest2", String.valueOf(response.getJwt()));
        }else {
            if(String.valueOf(response.getJwt()).equals("-1")){
                Toast.makeText(LoginActivity.this, "회원정보를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                Log.d("logintest2", String.valueOf(response.getJwt()));
            }else if (response.getJwt() > 0){
                jwt = response.getJwt();
                System.out.println("JWT입니다." + jwt);
                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                Log.d("logintest2", String.valueOf(jwt));
                startActivity(intent);
                finish();
            }
        }

    }
}