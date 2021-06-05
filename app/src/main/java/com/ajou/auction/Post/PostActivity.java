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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.ajou.auction.Post.Interface.PostActivityView;
import com.ajou.auction.Post.Service.PostService;
import com.ajou.auction.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.util.Calendar;

import static com.ajou.auction.BaseActivity.getCurrentTime;
import static com.ajou.auction.BaseActivity.getTodayDate;
import static com.ajou.auction.ApplicationClass.jwt;

public class PostActivity extends AppCompatActivity implements ImageInterface, PostActivityView {

    private static final String TAG = "Post";
    private final String init_date = getTodayDate();
    private final String init_time = getCurrentTime();


    private String selectedCategory; // 카테고리 이름
    private Long selectedCategoryNum = Long.valueOf(1); // 서버에 전달할 카테고리 번호 (초기 번호는 1)
    private String img_uri; // 서버에 전달할 이미지 url을 string으로 반환하여 전달
    private ImageButton btn_backToMain;
    private ImageView img_upload;
    private Button btn_post_write;
    private EditText et_post_content, et_post_title, et_post_price;
    private TextView tv_post_choose_date, tv_post_choose_time, tv_category;
    private boolean activity_stack_check = true;
    private int mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute;
    private String ENDDT = null, final_date = null;

    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;
    private Uri mImgUri;

    final int GET_GALLERY_IMAGE = 200;
    final int REQUEST_IMAGE_CODE = 1001;

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
                if (final_date == null) {
                    final_date = init_date + " " + init_time;
                }

                System.out.println("test    " + final_date + " " + selectedCategoryNum + " " + et_post_content.getText().toString() + " " + Long.valueOf(jwt) + " " + img_uri + " " + et_post_price.getText().toString() + "  " + et_post_title.getText().toString());
                tryPost(final_date, selectedCategoryNum, et_post_content.getText().toString(), Long.valueOf(jwt), img_uri, Long.valueOf(String.valueOf(et_post_price.getText())), et_post_title.getText().toString());

                SharedPreferences sharedPreferences = getSharedPreferences("categoryId", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                System.out.println("longlong" + selectedCategoryNum);
                editor.putLong("categoryId", selectedCategoryNum);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), CategoryListActivity.class);
                startActivity(intent);
                finish();
//                post();
            }
        });
        img_upload.setOnClickListener(new View.OnClickListener() {
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

        if (requestCode == REQUEST_IMAGE_CODE) {
            Uri image = data.getData();
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지뷰에 세팅
                    img_upload.setImageBitmap(img);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                uploadImage(image);
            }
        }
    }

    // Uri를 FireBase에 전송하고
    private void uploadImage(Uri imgUri) {
        final ImageService imageService = new ImageService(this);
        imageService.uploadFileToFireBase(imgUri);
    }

    // 경로 받아오기
    @Override
    public void uploadFireBaseSuccess(Uri uri) {
        mImgUri = uri;
        img_uri = mImgUri.toString();
        System.out.println("Image URI " + img_uri);
    }


    @Override
    public void uploadFireBaseFailure() {
        Toast.makeText(getApplicationContext(), "firebase upload fail ", Toast.LENGTH_SHORT).show();
    }

    private void tryPost(String auctionDeadLine, Long category, String content, Long jwt, String s3imageURL, Long startPrice, String title) {
        final PostService postService = new PostService(this);
        postService.postUploadPost(auctionDeadLine, category, content, jwt, s3imageURL, startPrice, title);
        System.out.println("try POST");
    }

    @Override
    public void postSuccess(String text) {
        System.out.println("PostSuccess");
    }

    @Override
    public void postFailure(String message) {
        System.out.println("PostFailure");
    }
}