package com.ajou.auction.Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.auction.Category.ViewPostActivity;
import com.ajou.auction.R;
import com.bumptech.glide.Glide;

public class MainFragment extends Fragment {

//    private List<String> items = Arrays.asList("아이폰", "맥북", "아이맥");
    private TextView tv_popular_title;
    private Button btn_popular;
    private ImageView img_gif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

       // TextView resultTextView = view.findViewById(R.id.menu1_temp1);

        SearchView searchView = view.findViewById(R.id.menu1_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { // 검색 버튼 눌렀을 떄
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) { // 타자칠 때마다 변함
                return false;
            }
        });

        tv_popular_title = view.findViewById(R.id.main_popular_title);

        btn_popular = view.findViewById(R.id.main_btn_popular);
        btn_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 실시간 인기 매물 api 받아서 호출해야함
                Intent intent = new Intent(getActivity(), ViewPostActivity.class);
                startActivity(intent);
            }
        });

        img_gif = view.findViewById(R.id.menu1_gif);
        Glide.with(view).load(R.raw.ads).into(img_gif);

        return view;
    }
}