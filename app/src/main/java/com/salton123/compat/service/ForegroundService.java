package com.salton123.compat.service;

import android.app.Service;

import com.salton123.saltonframeworkdemo.R;

/**
 * User: newSalton@outlook.com
 * Date: 2019/4/17 13:44
 * ModifyTime: 13:44
 * Description:
 */
public abstract class ForegroundService extends Service {
    public ForegroundDelegate mForegroundDelegate;

    public ForegroundService() {
        mForegroundDelegate = getForegroundDelegate();
    }

    private ForegroundDelegate getForegroundDelegate() {
        if (mForegroundDelegate == null) {
            ForegroundDelegate.Builder builder = new ForegroundDelegate.Builder(this)
                    .icon(R.mipmap.ic_emoji)
                    .title("测试服务标题")
                    .max(0)
                    .current(0)
                    .indeterminate(false)
                    .text("测试服务内容");
            mForegroundDelegate = new ForegroundDelegate(builder);
        }
        return mForegroundDelegate;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mForegroundDelegate.onForeground();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mForegroundDelegate.onBackground();
    }
}
