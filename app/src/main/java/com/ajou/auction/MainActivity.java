package com.ajou.auction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.ajou.auction.Category.CategoryFragment;
import com.ajou.auction.Chat.ChattingFragment;
import com.ajou.auction.Main.MainFragment;
import com.ajou.auction.My.MypageFragment;
import com.ajou.auction.Post.PostActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton mFloatingActionButton;

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private MainFragment mainFragment = new MainFragment();
    private CategoryFragment categoryFragment = new CategoryFragment();
    private ChattingFragment chattingFragment = new ChattingFragment();
    private MypageFragment mypageFragment = new MypageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, mainFragment).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.menu1: {
                        transaction.replace(R.id.frame_layout, mainFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu2: {
                        transaction.replace(R.id.frame_layout, categoryFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu3: {
                        transaction.replace(R.id.frame_layout, chattingFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu4: {
                        transaction.replace(R.id.frame_layout, mypageFragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        mFloatingActionButton = findViewById(R.id.main_btn_post);
    }
}