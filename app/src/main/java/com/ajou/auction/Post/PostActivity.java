package com.ajou.auction.Post;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajou.auction.R;

import java.util.Calendar;

import static com.ajou.auction.BaseActivity.getTodayDate;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "Post";
    private final String init_time = getTodayDate();

    private ImageButton btn_backToMain;
    private ImageView img_upload;
    private Button btn_post_write;
    private EditText et_post_content, et_post_title;
    private TextView tv_post_choose_date;
    private boolean activity_stack_check = true;
    private int mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute, mEndSecond;
    private String ENDDT = null;

    final int GET_GALLERY_IMAGE = 200;
    final int REQUEST_IMAGE_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        init();
        btnMover();
        System.out.println(getTodayDate()); // 날짜, 시간 가져오기

        tv_post_choose_date.setText(init_time);
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
                        System.out.println("선택된 시작 날짜 : " + enddt);
                        tv_post_choose_date.setText(enddt);

                        ENDDT = enddt;
                    }
                }, mYear, mMonth, mDay);
                mDatePickerDialog.show();
            }
        });
    }

    public void init() {
        et_post_content = (EditText) findViewById(R.id.post_write_et_content);
        et_post_title = (EditText) findViewById(R.id.post_write_et_title);
        btn_post_write = (Button) findViewById(R.id.post_write_btn_complete);
        btn_backToMain = (ImageButton) findViewById(R.id.post_write_btn_close);
        img_upload = (ImageView) findViewById(R.id.post_write_btn_img);
        tv_post_choose_date = (TextView) findViewById(R.id.post_tv_date);
    }

    public void btnMover(){
        btn_backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(PostActivity.this)
                        .setMessage("뒤로가시면 내용이 저장되지 않습니다.")
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
                post();
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
}