package com.yy.saltonframework.base;

import android.app.Activity;
import android.app.Application;

import com.yy.saltonframework.app.BaseCrashHandler;
import com.yy.saltonframework.app.RebootThreadExceptionHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2015-08-06
 * Time: 12:23
 * Desc:捕获应用异常Application以及完成整个应用退出；在这里进行全局变量的传递；在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置, 预加载处理
 */


public class ApplicationBase extends Application {

    /**
     * activity栈保存
     */
    public List<Activity> activityStack = null;

    @Override
    public void onCreate() {
        super.onCreate();
        // activity管理
        activityStack = new ArrayList<Activity>();
    }

    /**
     * 程序异常关闭1s之后重新启动
     */
    public void openRestartHandler() {
        new RebootThreadExceptionHandler(getBaseContext());
    }

    /**
     * 异常处理收集
     */
    public void openCrashHanlder() {
        BaseCrashHandler handler = BaseCrashHandler.getInstance();
        handler.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    // 在内存低时,发送广播可以释放一些内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 退出整个应用
     */
    public void exitApp() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

}
