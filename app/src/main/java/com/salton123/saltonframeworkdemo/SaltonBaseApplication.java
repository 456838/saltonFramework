package com.salton123.saltonframeworkdemo;

import android.content.Context;
import android.os.Debug;

import com.salton123.app.BaseApplication;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/11 下午11:25
 * ModifyTime: 下午11:25
 * Description:
 */
public class SaltonBaseApplication extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


    }

    @Override
    public void onCreate() {
        super.onCreate();
        Debug.startMethodTracing("/sdcard/183/debug1.trace");
    }
}
