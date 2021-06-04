package com.ajou.auction.Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.auction.Category.ViewPostActivity;
import com.ajou.auction.R;
import com.bumptech.glide.Glide;

public class MainFragment extends Fragment {

    private EditText et_search;
    private TextView tv_popular_title;
    private Button btn_popular, btn_search;
    private ImageView img_gif;
    static public String keyWord; // 검색 문자열

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        et_search = view.findViewById(R.id.main_et_search);
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

        btn_search = view.findViewById(R.id.main_btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyWord = et_search.getText().toString();
                System.out.println("검색 단어 : " + keyWord);

                // keyword에 해당하는 단어를 포함해서 서버로

                Intent intent = new Intent(getActivity(), SearchListActivity.class);
                startActivity(intent);
            }
        });

        img_gif = view.findViewById(R.id.menu1_gif);
        Glide.with(view).load(R.raw.ads).into(img_gif);

        return view;
    }
}