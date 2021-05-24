package com.ajou.auction;

import android.app.Application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseActivity extends Application {
    public static String BASE_URL = "";

    public static String getTodayDate() {
        // 오늘의 날짜
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("y");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM");
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("dd");
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("HH:mm:ss");

        int init_year = Integer.parseInt(simpleDateFormat1.format(mDate));
        int init_month = Integer.parseInt(simpleDateFormat2.format(mDate));
        int init_day = Integer.parseInt(simpleDateFormat3.format(mDate));

        String st_init_month = null;
        String st_init_day = null;
        String st_init_time = simpleDateFormat4.format(mDate);

        if (init_month < 10) {
            st_init_month = "0" + init_month;
        } else {
            st_init_month = Integer.toString(init_month);
        }
        if (init_day < 10) {
            st_init_day = "0" + init_day;
        }
        else {
            st_init_day = Integer.toString(init_day);
        }

        final String init_dt = init_year + "-" + st_init_month + "-" + st_init_day;

        return init_dt;
    }

    public static String getCurrentTime() {
        // 현재 시간 가져오기
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        String st_init_time = simpleDateFormat.format(mDate);

        return st_init_time;
    }
}
