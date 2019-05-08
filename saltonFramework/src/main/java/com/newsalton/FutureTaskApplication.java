package com.newsalton;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.CountDownLatch;


/**
 * User: newSalton@outlook.com
 * Date: 2019-05-08 23:58
 * ModifyTime: 23:58
 * Description:每个进程都会执行Application的初始化操作，对此我们需要在主进程区别初始化
 */
public class FutureTaskApplication extends Application implements IFutureTaskPriority {
    //只有高优先级的任务需要和主线程保持同步，其他线程的任务异步处理，加快初始化过程。
    private CountDownLatch mCountDownLatch = new CountDownLatch(1);

    //走在最前
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (CommonUtils.isMainProcess()) {  //主进程

        }
    }

    @Override
    public void highPriority() {

    }

    @Override
    public void mediumPriority() {

    }

    @Override
    public void lowPriority() {

    }
}
