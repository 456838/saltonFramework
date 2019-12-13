package com.salton123.saltonframeworkdemo;

import android.os.Bundle;

import com.kingja.loadsir.callback.Callback;
import com.salton123.feature.PermissionFeature;
import com.salton123.feature.multistatus.LoadingStatus;
import com.salton123.feature.multistatus.MultiStatusFeature;
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
    }

    @Override
    public void initViewAndData() {
        asynTitleBar(null);
    }

    public MultiStatusFeature getMultiStatusFeature() {
        return new MultiStatusFeature(this) {
            @Override
            public Callback getInitStatus() {
                LoadingStatus loadingStatus = new LoadingStatus();
                // HintCallback hintCallback = new HintCallback.Builder()
                //         .setTitle("Error", com.salton123.saltonframework.R.style.salton_hint_title)
                //         .setSubTitle("Sorry, buddy, I will try it again.")
                //         .setHintImg(com.salton123.saltonframework.R.drawable.salton_load_pic_failed)
                //         .build();
                return loadingStatus;
            }
        };
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
