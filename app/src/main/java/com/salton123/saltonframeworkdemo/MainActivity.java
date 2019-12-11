package com.salton123.saltonframeworkdemo;

import android.os.Bundle;

import com.salton123.feature.BlackTitleFeature;
import com.salton123.feature.PermissionFeature;
import com.salton123.ui.base.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public void initVariable(Bundle savedInstanceState) {
        addFeature(new PermissionFeature());
        addFeature(new BlackTitleFeature(this) {
            @Override
            public String getLeftText() {
                return "返回";
            }

            @Override
            public String getRightText() {
                return "确定";
            }

            @Override
            public String getTitle() {
                return "和明天的聊天";
            }

            @Override
            public String getSubTitle() {
                return "距离100km";
            }
        });
    }

    @Override
    public void initViewAndData() {

    }

    private String createFile() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String time = formatter.format(new Date());
        return "crash_" + time + ".txt";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
