package com.ajou.auction.Post;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ajou.auction.Category.CategoryListActivity;
import com.ajou.auction.Login.LoginActivity;
import com.ajou.auction.Login.Services.LogInService;
import com.ajou.auction.Post.Interface.PostActivityView;
import com.ajou.auction.Post.Interface.PostS3ImageActivityView;
import com.ajou.auction.Post.Model.PostS3ImageBody;
import com.ajou.auction.Post.Model.PostS3ImageResponse;
import com.ajou.auction.Post.Service.PostS3ImageService;
import com.ajou.auction.Post.Service.PostService;
import com.ajou.auction.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

import static com.ajou.auction.BaseActivity.getCurrentTime;
import static com.ajou.auction.BaseActivity.getTodayDate;
import static com.ajou.auction.ApplicationClass.jwt;
import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;

public class PostActivity extends AppCompatActivity implements PostS3ImageActivityView, PostActivityView {

    private static final String TAG = "Post";
    private final String init_date = getTodayDate();
    private final String init_time = getCurrentTime();


    private String selectedCategory; // 카테고리 이름
    private Long selectedCategoryNum = Long.valueOf(1); // 서버에 전달할 카테고리 번호 (초기 번호는 1)
    //롤렉스,구찌신발,폴로셔츠,범고래,샤넬백,향수,패딩,iab,아디다스운동화
    private String img_idx1_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/56155dc4-9f37-4ae4-b6e5-c3efe4b16726.png"; // 서버에 전달할 이미지 url을 string으로 반환하여 전달
    private String img_idx1_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/ff90af98-146f-419a-9361-87b3b94e840e.jpg";
    private String img_idx1_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/5a261c0b-aae2-40a7-a97b-74c57181447c.jpg";
    private String img_idx1_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/6f3694ff-4b49-49f5-9728-329f0694db25.jpg";
    private String img_idx1_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/f274f436-4070-44bf-8a5d-b38f3d0b6536.jpg";
    private String img_idx1_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/62ee029f-fc23-4175-8166-f6148fb96499.jpg";
    private String img_idx1_7 = "https://myitem.s3.ap-northeast-2.amazonaws.com/27382e75-f416-4b42-a79c-1f6ea4d9f975.jpg";
    private String img_idx1_8 = "https://myitem.s3.ap-northeast-2.amazonaws.com/4dc20023-7bc0-4c0c-8794-f5a8093b707a.png";
    private String img_idx1_9 ="https://myitem.s3.ap-northeast-2.amazonaws.com/18860c0a-417c-419a-8b6f-6e44a5ac60d7.jpg";

    //디올1,디올2,디올3,입생1,입생2,입생3,마스카라,남자팔찌,목걸이,헤어왁스
    private String img_idx2_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/a53a6121-3067-472e-96a8-7588cd6b055c.jpg";
    private String img_idx2_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/e754e8cf-0b29-4cf7-917b-333d9e6233d6.jpg";
    private String img_idx2_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/078daaf7-2c2a-41c7-bbd4-d0a95a5be641.jpg";
    private String img_idx2_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/52127221-55cb-4acc-9d9d-a3fd2187a87c.jpg";
    private String img_idx2_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/6f52d720-f7bf-4a26-8eff-55a33b922cbd.jpg";
    private String img_idx2_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/3515d742-9d4f-4512-b54b-eb194996d12f.jpg";
    private String img_idx2_7 = "https://myitem.s3.ap-northeast-2.amazonaws.com/df6a5882-b73b-4e04-9fc7-83d96d739263.png";
    private String img_idx2_8 = "https://myitem.s3.ap-northeast-2.amazonaws.com/f44bbb0c-fd6c-46cb-be1c-95ee58e79c07.png";
    private String img_idx2_9 = "https://myitem.s3.ap-northeast-2.amazonaws.com/9d8a7d11-1478-4ccf-9e3b-97ff6ddb3a24.png";
    private String img_idx2_10 = "https://myitem.s3.ap-northeast-2.amazonaws.com/6bee836c-0310-4b7c-99e6-faf67b6b89ca.png";


    //버즈,에엉팟,TV,모니터,에어컨,맥북,에어프라이어,냉장고,무소음 선풍기
    private String img_idx3_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/05328282-a768-44f4-8b19-64ecf401cb23.jpg";
    private String img_idx3_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/54cf54db-c314-4c84-a75d-90fd909e0451.jpg";
    private String img_idx3_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/0e64f728-1b7a-46a7-8685-9c76f1b3b9c7.jpg";
    private String img_idx3_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/fd401c82-5fc6-421a-af30-03e8f17068b1.jpg";
    private String img_idx3_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/427d3c76-b3af-49a7-bb74-4e282b24a1b7.jpg";
    private String img_idx3_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/c3c3a2ce-eb76-486a-b89a-91a7824c2111.jpg";
    private String img_idx3_7 = "https://myitem.s3.ap-northeast-2.amazonaws.com/ebfaacec-6f53-455f-920c-c6bd16ef3b27.png";
    private String img_idx3_8 = "https://myitem.s3.ap-northeast-2.amazonaws.com/06524ba8-69c0-4eec-baad-18e0bded27fe.png";
    private String img_idx3_9 = "https://myitem.s3.ap-northeast-2.amazonaws.com/ba5efdb2-baa2-474b-8e4a-37984bdf6510.png";

    //철제,소파,식탁,책장,샹들레,전등,소파2,식탁,디지털 벽시계, 벽시계
    private String img_idx4_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/7c7e1659-aecd-42eb-aa9d-db8b7f95068c.jpg";
    private String img_idx4_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/e3047697-5c9b-47c4-ab1d-c28d0c5a5fb0.jpg";
    private String img_idx4_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/04270186-6128-48fa-a44c-4065b05aa253.jpg";
    private String img_idx4_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/5cdf3db9-7a35-4f57-afda-2d4c928c4ce5.jpg";
    private String img_idx4_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/d12077e5-5720-433c-b971-0e3617dde09f.jpg";
    private String img_idx4_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/a62d6a9f-adce-459d-923a-f4a6b08ca814.jpg";
    private String img_idx4_7 = "https://myitem.s3.ap-northeast-2.amazonaws.com/8038a43b-9eb7-478a-a33f-631e915ec832.png";
    private String img_idx4_8 = "https://myitem.s3.ap-northeast-2.amazonaws.com/5f44b74a-ec62-4a58-b7b8-3300fcfd0145.png";
    private String img_idx4_9 ="https://myitem.s3.ap-northeast-2.amazonaws.com/4f6a0572-8877-443b-8b79-9bb6b315cc06.png";
    private String img_idx4_10 = "https://myitem.s3.ap-northeast-2.amazonaws.com/0562ed3d-a1d2-42ac-9f3f-e8af6ce4bb15.png";

    //텀블러1,2,3,후라이팬,스팸,한우,카레,곰표맥주,로이스초콜릿,진라면
    private String img_idx5_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/f5feaa01-f7c1-4448-8720-f4186daac5a3.jpg";
    private String img_idx5_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/e8d1bdc0-20a1-46a8-9ed7-df1943e4a56b.jpg";
    private String img_idx5_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/d10792d6-a290-410e-aeff-0fac8b7781ad.jpg";
    private String img_idx5_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/40859de8-8fa8-44ab-b576-8bd91c747c5b.jpg";
    private String img_idx5_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/383de6b1-8a83-49cd-a1c8-30140e68c22e.jpg";
    private String img_idx5_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/06645ff6-3258-4be7-9b0e-85269df83488.jpg";
    private String img_idx5_7 = "https://myitem.s3.ap-northeast-2.amazonaws.com/6e82f456-f4a5-49aa-a13d-d0cd9baf18dc.png";
    private String img_idx5_8 = "https://myitem.s3.ap-northeast-2.amazonaws.com/a4672ac0-14ac-46d0-9e5d-d2a4cf7958fb.png";
    private String img_idx5_9 = "https://myitem.s3.ap-northeast-2.amazonaws.com/44a268f7-6fe0-46c0-bc36-f35980eaced5.png";
    private String img_idx5_10 = "https://myitem.s3.ap-northeast-2.amazonaws.com/8516bed0-eeac-4c07-bd1e-6097659f80dc.png";
    //인라인,엠티비,축구화1,축구화2,골프채,농구공,골프채2,몰텐농구공,축구공,턱걸이기구
    private String img_idx6_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/60b555dd-a09e-43e3-81b6-713d290e2be8.jpg";
    private String img_idx6_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/e0415385-ea5d-459a-8230-197361b053c0.jpg";
    private String img_idx6_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/ab95286f-21a9-47ce-b3a5-21b5e4ea2445.jpg";
    private String img_idx6_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/e3556157-d11f-405d-bc55-43cb9333edff.jpg";
    private String img_idx6_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/42b2d123-6e60-47c5-b5f6-f9a48e125053.jpg";
    private String img_idx6_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/d5da9d3c-1e93-42c7-8ebd-aeba9f0cc483.jpg";
    private String img_idx6_7 = "https://myitem.s3.ap-northeast-2.amazonaws.com/32ae155e-c4b8-4fc6-b635-0518c96a0459.jpg";
    private String img_idx6_8 = "https://myitem.s3.ap-northeast-2.amazonaws.com/4ec717aa-58d3-4aca-a5c2-5a509eb26024.png";
    private String img_idx6_9 = "https://myitem.s3.ap-northeast-2.amazonaws.com/98de38f9-179e-46d9-b4ca-418cdeb51a90.png";
    private String img_idx6_10 = "https://myitem.s3.ap-northeast-2.amazonaws.com/f1472331-424f-44c9-87e0-299efc72a259.jpg";
    //vr,스위치,플스,오락실게임기,위,키보드,리틀파이터게임기,레트로게임기
    private String img_idx7_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/7c44ea70-53d2-41ac-a9fe-ab255e9d15d4.jpg";
    private String img_idx7_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/dcadc3ce-6b18-4c26-8b70-a938394e1e98.jpg";
    private String img_idx7_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/cdc4dfbf-c255-4395-bdda-92087a2e369c.jpg";
    private String img_idx7_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/7cbd7879-5ece-41b0-b7ba-070f63506ab0.jpg";
    private String img_idx7_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/1bb48bd6-0027-4980-aced-44dae0e4c16e.jpg";
    private String img_idx7_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/622d2482-c121-4497-980b-7f37d3d2b380.png";
    private String img_idx7_7 ="https://myitem.s3.ap-northeast-2.amazonaws.com/3228b1b2-0aa7-4d57-95c8-994d0db1bcca.jpg";
    private String img_idx7_8 ="https://myitem.s3.ap-northeast-2.amazonaws.com/5d2ba728-2c51-4d3a-bc5e-464df64ca720.jpg";
    //엑소인형세트,방탄앨범,정처기책,유아도서,티켓,기하급수책,나훈아 앨범,트와이스 앫범, 대학물리학1
    private String img_idx8_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/e96bb2b9-d6c8-419f-9c53-d936869a53a1.jpg";
    private String img_idx8_2 = "https://myitem.s3.ap-northeast-2.amazonaws.com/f9fc8976-09e5-4c00-9ad3-f87c1fb11994.jpg";
    private String img_idx8_3 = "https://myitem.s3.ap-northeast-2.amazonaws.com/332a7d62-e99b-4b45-b8ff-32c4839305b3.jpg";
    private String img_idx8_4 = "https://myitem.s3.ap-northeast-2.amazonaws.com/f1a0b274-cd07-4169-abc4-2078d361892b.jpg";
    private String img_idx8_5 = "https://myitem.s3.ap-northeast-2.amazonaws.com/15b0c155-4259-4d63-b771-ccbc8e0000fd.jpg";
    private String img_idx8_6 = "https://myitem.s3.ap-northeast-2.amazonaws.com/6ee7452e-73b5-4432-8ebc-02ec250cbd7e.jpg";
    private String img_idx8_7 = "https://myitem.s3.ap-northeast-2.amazonaws.com/f777d70d-b46b-4bd4-92ea-d0eaf3e86631.png";
    private String img_idx8_8 = "https://myitem.s3.ap-northeast-2.amazonaws.com/c4a669f2-94bf-425a-b76b-7a775636d682.jpg";
    private String img_idx8_9 = "https://myitem.s3.ap-northeast-2.amazonaws.com/196c1989-bf76-4c0b-8df1-0d706c8de9ea.png";
    private String img_idx8_10 = "https://myitem.s3.ap-northeast-2.amazonaws.com/8b2cf3c3-4204-434c-9a81-4c698402da50.jpg";
    //기타/무료나눔
    //네이버페이상품권,수학과외,스벅쿠폰,아이오닉,콴다 코인,메이저리그티켓
    private String img_idx9_1 = "https://myitem.s3.ap-northeast-2.amazonaws.com/6a6891bd-91c8-4214-83fe-30f0f4cc2971.png";
    private String img_idx9_2 ="https://myitem.s3.ap-northeast-2.amazonaws.com/f3ef983c-3926-4cd6-9448-d0087317ceeb.png";
    private String img_idx9_3 ="https://myitem.s3.ap-northeast-2.amazonaws.com/3b325e1c-c16d-4058-b9ef-2b1cec2fe4aa.png";
    private String img_idx9_4 ="https://myitem.s3.ap-northeast-2.amazonaws.com/26025180-c3b8-4ac6-b2b0-bceca264b6cc.png";
    private String img_idx9_5 ="https://myitem.s3.ap-northeast-2.amazonaws.com/72481555-54dd-4b18-b69c-7fb86d46d865.png";
    private String img_idx9_6 ="https://myitem.s3.ap-northeast-2.amazonaws.com/6c447f34-1a0b-4836-97e4-a0299ba48ef2.jpg";

    private ImageButton btn_backToMain,btn_addImage;
    private ImageView img_upload;
    private Button btn_post_write;
    private EditText et_post_content, et_post_title, et_post_price;
    private TextView tv_post_choose_date, tv_post_choose_time, tv_category;
    private boolean activity_stack_check = true;
    private int mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute;
    private String ENDDT = null, final_date = null;

//    private FirebaseFirestore db;
//    private FirebaseUser firebaseUser;
    private Uri gettedImgUri;

    final int GET_GALLERY_IMAGE = 200;
    final int REQUEST_IMAGE_CODE = 1001;

    File tempSelectFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        init();
        btnMover();
        System.out.println(getTodayDate()); // 날짜, 시간 가져오기

        tv_post_choose_date.setText(init_date);
        tv_post_choose_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePickerDialog;
                mDatePickerDialog = new DatePickerDialog(PostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // i : year, i1 : month, i2 : day

                        mEndYear = i;
                        mEndMonth = i1 + 1;
                        mEndDay = i2;

                        // i1에서 1월이면 0, 2월이면 1, 3월이면 2가 나오므로 1을 더해줘야함
                        String enddt;
                        System.out.println("i : " + mEndYear + "  i1 : " + mEndMonth + "  i2 : " + mEndDay);

                        // 날짜를 2021-01-01 형식으로 맞춰주기
                        if (mEndMonth < 10) {
                            enddt = i + "-0" + mEndMonth + "-";
                        } else {
                            enddt = i + "-" + mEndMonth + "-";
                        }

                        if (i2 < 10) {
                            enddt = enddt + "0" + mEndDay;
                        }
                        else {
                            enddt = enddt + mEndDay;
                        }
                        System.out.println("선택된 마감 날짜 : " + enddt);
                        tv_post_choose_date.setText(enddt);

                        final_date = enddt;

                        ENDDT = enddt;
                    }
                }, mYear, mMonth, mDay);
                mDatePickerDialog.show();
            }
        });

        tv_post_choose_time.setText(init_time);
        tv_post_choose_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(PostActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        // i : year, i1 : month, i2 : day

                        mEndHour = i;
                        mEndMinute = i1;
                        System.out.println("i : " + mEndHour + "  i1 : " + mEndMinute);

                        String endtime;
                        if (mEndHour < 10) {
                            endtime = "0" + mEndHour + ":";
                        } else {
                            endtime = mEndHour + ":";
                        }

                        System.out.println(endtime);
                        if (mEndMinute < 10) {
                            endtime = endtime + "0" + mEndMinute;
                        } else {
                            endtime = endtime + mEndMinute;
                        }

//                        endtime = mEndHour + ":" + mEndMinute;
                        System.out.println("선택된 마감 시간 : " + endtime);
                        tv_post_choose_time.setText(endtime);

                        final_date = final_date + " " + endtime;
                    }
                }, mEndHour, mEndMinute, true);
                timePickerDialog.show();
            }
        });


        tv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items = new String[] {"의류/잡화", "뷰티", "디지털/가전", "가구/인테리어", "생활/가공식품", "스포츠/레저", "게임/취미", "도서/티켓/음반", "기타/무료나눔"};
                final int[] selectedIndex = {0};

                AlertDialog.Builder dialog = new AlertDialog.Builder(PostActivity.this);
                dialog.setTitle("카테고리를 선택해주세요.")
                        .setSingleChoiceItems(items,0
                                ,new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        selectedIndex[0] = i;
                                        selectedCategoryNum = Long.valueOf(i + 1);

                                        System.out.println("PostActivity에서 서버에 전달할 카테고리 번호 : " + selectedCategoryNum + "번");
                                    }
                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tv_category.setText(items[selectedIndex[0]]);

                                System.out.println("PostActivity에서 선택된 카테고리 이름  : " + items[selectedIndex[0]]);
                            }
                        }).create().show();
            }
        });

    }

    public void init() {
        btn_addImage = (ImageButton) findViewById(R.id.btn_addImage);
        et_post_content = (EditText) findViewById(R.id.post_write_et_content);
        et_post_title = (EditText) findViewById(R.id.post_write_et_title);
        et_post_price = (EditText) findViewById(R.id.post_write_et_price);
        btn_post_write = (Button) findViewById(R.id.post_write_btn_complete);
        btn_backToMain = (ImageButton) findViewById(R.id.post_write_btn_close);
        img_upload = (ImageView) findViewById(R.id.post_write_btn_img);
        tv_post_choose_date = (TextView) findViewById(R.id.post_tv_date);
        tv_post_choose_time = (TextView) findViewById(R.id.post_tv_time);
        tv_category = (TextView) findViewById(R.id.post_write_btn_category);
    }

    public void btnMover(){
        btn_backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(PostActivity.this)
                        .setMessage("뒤로 가시면 내용이 저장되지 않습니다.")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });
        btn_post_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(PostActivity.this)
                        .setMessage("내 물건을 경매에 등록하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (final_date == null) {
                                    final_date = init_date + " " + init_time;
                                }

                                //System.out.println("test    " + final_date + " " + selectedCategoryNum + " " + et_post_content.getText().toString() + " " + Long.valueOf(jwt) + " " + img_uri + " " + et_post_price.getText().toString() + "  " + et_post_title.getText().toString());
                                tryPost(final_date, selectedCategoryNum, et_post_content.getText().toString(), Long.valueOf(jwt), img_idx4_1, Long.valueOf(String.valueOf(et_post_price.getText())), et_post_title.getText().toString());
                                SharedPreferences sharedPreferences = getSharedPreferences("categoryId", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                System.out.println("longlong" + selectedCategoryNum);
                                editor.putLong("categoryId", selectedCategoryNum);
                                editor.apply();

                                Intent intent = new Intent(getApplicationContext(), CategoryListActivity.class);
                                startActivity(intent);
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();

            }
        });
        btn_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_CODE);
            }
        });
    }

    private void post(){
        final String title = et_post_title.getText().toString();
        final String content = et_post_content.getText().toString();


        if (title.length() > 0 && content.length() > 0){

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== REQUEST_IMAGE_CODE && resultCode==RESULT_OK && data!=null) {
            Uri selectedImage = data.getData();

            //여기서부터
            Uri photoUri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),photoUri);
                //bitmap = rotateImage(bitmap, 90);
            } catch (IOException e) {
                e.printStackTrace();
            }
            img_upload.setImageBitmap(bitmap);

            //여기까지는 이미지를 갤러리에서 클릭해서 그 사진을 이미지뷰에 보여주는 코드

//            Cursor cursor = getContentResolver().query(Uri.parse(selectedImage.toString()), null, null, null, null);
//            assert cursor != null;
//            cursor.moveToFirst();
//            String mediaPath = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
//            //커서 사용해서 경로 확인
//            Log.d("s3",mediaPath);
//            if(mediaPath != null){
//                File file = new File(mediaPath);
//                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
//                Log.d("s3", String.valueOf(requestBody));
//                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName());
//                Log.d("s3", String.valueOf(fileToUpload));
//                new PostS3ImageService(PostActivity.this).postS3Image(fileToUpload);
//            }
        }else{
            Toast.makeText(this, "사진 업로드 실패", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void postS3UploadSuccess(PostS3ImageResponse response) {
        Toast.makeText(PostActivity.this, "이미지 S3등록 성공" + response.getImgUrl(), Toast.LENGTH_SHORT).show();
        Log.d("s3", response.getImgUrl());
    }

    @Override
    public void postS3UploadFailure() {
        Toast.makeText(PostActivity.this, "이미지 S3등록 실패", Toast.LENGTH_SHORT).show();
        Log.d("s3", "fail");
    }


//    // Uri를 서버에 전송하고
//    private void uploadImage(Uri imgUri) {
//        new PostS3ImageService(PostActivity.this).postS3Image(
//                img_uri.toString()
//        );
//    }

//    // 경로 받아오기
//    @Override
//    public void uploadFireBaseSuccess(Uri uri) {
//        mImgUri = uri;
//        img_uri = mImgUri.toString();
//        System.out.println("Image URI " + img_uri);
//    }
//
//
//    @Override
//    public void uploadFireBaseFailure() {
//        Toast.makeText(getApplicationContext(), "firebase upload fail ", Toast.LENGTH_SHORT).show();
//    }

    private void tryPost(String auctionDeadLine, Long category, String content, Long jwt, String s3imageURL, Long startPrice, String title) {
        final PostService postService = new PostService(this);
        postService.postUploadPost(auctionDeadLine, category, content, jwt, s3imageURL, startPrice, title);
        System.out.println("try POST");
    }

    @Override
    public void postSuccess(String text) {
        Toast.makeText(this,"배팅 게시물 등록 성공",Toast.LENGTH_SHORT).show();
        System.out.println("PostSuccess");
    }

    @Override
    public void postFailure(String message) {
        System.out.println("PostFailure");
    }


}