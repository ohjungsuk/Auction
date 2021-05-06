package com.ajou.auction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setUserData(){
        et_login_id = (EditText) findViewById(R.id.login_et_id);
        et_login_password = (EditText) findViewById(R.id.login_et_password);
        btn_login = (Button)findViewById(R.id.login_btn);
        btn_signup = findViewById(R.id.login_btn_signup);
    }
}