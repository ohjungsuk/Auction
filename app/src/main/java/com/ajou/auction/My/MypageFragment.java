package com.ajou.auction.My;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Login.Interfaces.SignOutActivityView;
import com.ajou.auction.Login.Interfaces.SignOutRetrofitInterface;
import com.ajou.auction.Login.LoginActivity;
import com.ajou.auction.Login.Services.SignOutService;
import com.ajou.auction.Login.SignUpActivity;
import com.ajou.auction.MainActivity;
import com.ajou.auction.Profile.FollowerActivity;
import com.ajou.auction.Profile.ProfileReviewActivity;
import com.ajou.auction.R;

import static com.ajou.auction.ApplicationClass.jwt;

public class MypageFragment extends Fragment implements SignOutActivityView {
    Button mypage_signout,mypage_logout;
    private LinearLayout btn_follower, btn_comment, btn_like;
    private TextView txtt;
    private Button goto_boardlist;
    private Context mContext;


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

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("myId", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("myId", "");

        txtt.setText(userId);

        goto_boardlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fragmentToActivity(10L);
                Intent intent = new Intent(getActivity(), CategoryListActivity.class);
                intent.putExtra("isMyBoard", true);
                intent.putExtra("from","MF");
                startActivity(intent);

            }
        });

        btn_follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FollowerActivity.class);
                startActivity(intent);
            }
        });

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LikedListActivity.class);
                startActivity(intent);
            }
        });

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ?????? ID ??????
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("ID", Context.MODE_PRIVATE);
                String userId = sharedPreferences.getString("user_id", "");
                System.out.println("???????????? ????????? Id ?????? " + userId);

                sharedPreferences = getContext().getSharedPreferences("UserId", Context.MODE_PRIVATE);

                /*
                sharedPreferences = getSharedPreferences("UserId", Context.MODE_PRIVATE);
        String userRealId = sharedPreferences.getString("userRealId", "");
                 */
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userRealId", userId);
                editor.apply();
                System.out.println("???????????? User Id ????????? " + userId);


                Intent intent = new Intent(getActivity(), ProfileReviewActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void init(View view){
        txtt = view.findViewById(R.id.txtt);
        mypage_logout = view.findViewById(R.id.mypage_logout);
        mypage_signout = view.findViewById(R.id.mypage_signout);
        btn_follower = view.findViewById(R.id.menu4_btn_follower);
        btn_comment = view.findViewById(R.id.menu4_btn_comment);
        btn_like = view.findViewById(R.id.menu4_btn_like);
        goto_boardlist = view.findViewById(R.id.goto_boardlist);
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
                        .setMessage("???????????????????????????????")
                        .setPositiveButton("???", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                new SignOutService(MypageFragment.this).deleteAcc(jwt);

                            }
                        })
                        .setNegativeButton("?????????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });
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

    @Override
    public void validateSuccess() {
        Toast.makeText(getContext(), "???????????? ??????", Toast.LENGTH_SHORT).show();
        Log.d("logintest2", String.valueOf(jwt));
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void validateFailure() {
        Toast.makeText(getContext(), "???????????? ??????", Toast.LENGTH_SHORT).show();
    }
}