package com.ajou.auction.Category;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ajou.auction.R;
import com.bumptech.glide.Glide;

public class CategoryFragment extends Fragment {

    private LinearLayout btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Context mContext;
    private ImageView img_gif;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mContext = view.getContext();

        btn1 = view.findViewById(R.id.menu2_btn_clothes);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity(Long.valueOf(new Integer(1)));
            }
        });

        btn2 = view.findViewById(R.id.menu2_btn_beauty);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity(Long.valueOf(2));
            }
        });

        btn3 = view.findViewById(R.id.menu2_btn_digital);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity(Long.valueOf(3));
            }
        });

        btn4 = view.findViewById(R.id.menu2_btn_furniture);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity((long) 4);
            }
        });

        btn5 = view.findViewById(R.id.menu2_btn_food);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity((long) 5);
            }
        });

        btn6 = view.findViewById(R.id.menu2_btn_sports);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity((long) 6);
            }
        });

        btn7 = view.findViewById(R.id.menu2_btn_game);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity((long) 7);
            }
        });

        btn8 = view.findViewById(R.id.menu2_btn_book);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity((long) 8);
            }
        });

        btn9 = view.findViewById(R.id.menu2_btn_etc);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentToActivity((long) 9);
            }
        });

        img_gif = view.findViewById(R.id.menu2_gif);
        Glide.with(view).load(R.raw.ads2).into(img_gif);
        return view;
    }

    private void fragmentToActivity(Long categoryId) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("categoryId", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println("longlong" + categoryId);
        editor.putLong("categoryId", categoryId);
        editor.apply();

        Intent intent = new Intent(getActivity(), CategoryListActivity.class);
        startActivity(intent);
    }
}