package com.salton123.saltonframeworkdemo;

import android.view.View;

import com.salton123.base.ApplicationBase;
import com.salton123.saltonframeworkdemo.floatingview.FloatingViewManager;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/16 11:28
 * ModifyTime: 11:28
 * Description:
 */
public class SaltonApplication extends ApplicationBase {
    @Override
    public void onCreate() {
        super.onCreate();
        FloatingViewManager.INSTANCE.init(this);
        FloatingViewManager.INSTANCE.setCurrentView(View.inflate(this, R.layout.view_floating, null));
    }
}
