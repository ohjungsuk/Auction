package com.ajou.auction.My;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Login.Interfaces.SignOutActivityView;
import com.ajou.auction.Login.Interfaces.SignOutRetrofitInterface;
import com.ajou.auction.Login.LoginActivity;
import com.ajou.auction.Login.Services.SignOutService;
import com.ajou.auction.Login.SignUpActivity;
import com.ajou.auction.MainActivity;
import com.ajou.auction.R;

import static com.ajou.auction.ApplicationClass.jwt;

public class MypageFragment extends Fragment implements SignOutActivityView {
    Button mypage_signout,mypage_logout;
    private LinearLayout btn_follower, btn_comment, btn_like;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        init(view);
        btn_mover(view);

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LikedListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void init(View view){
        mypage_logout = view.findViewById(R.id.mypage_logout);
        mypage_signout = view.findViewById(R.id.mypage_signout);
        btn_follower = view.findViewById(R.id.menu4_btn_follower);
        btn_comment = view.findViewById(R.id.menu4_btn_comment);
        btn_like = view.findViewById(R.id.menu4_btn_like);
    }

    public void btn_mover(View view){
        mypage_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mypage_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("회원탈퇴하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                new SignOutService(MypageFragment.this).deleteAcc(jwt);

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
    public void validateSuccess() {
        Toast.makeText(getContext(), "회원탈퇴 성공", Toast.LENGTH_SHORT).show();
        Log.d("logintest2", String.valueOf(jwt));
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void validateFailure() {
        Toast.makeText(getContext(), "회원탈퇴 성공", Toast.LENGTH_SHORT).show();
    }
}