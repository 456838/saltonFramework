package com.salton123.app;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;

import com.salton123.C;
import com.salton123.app.crash.SaltonCrashHandler;
import com.salton123.app.future.FutureTaskApplication;
import com.salton123.log.XLog;
import com.salton123.log.XLogConfig;
import com.salton123.model.manager.lifecycle.IActivityLifeCycle;

import java.io.File;

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/9 17:50
 * ModifyTime: 17:50
 * Description:
 */
public class BaseApplication extends FutureTaskApplication {
    public static Application sInstance;

    public static <T extends Application> T getInstance() {
        return (T) sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sInstance = this;
    }

    @Override
    public void lowPriority() {
        super.lowPriority();
        openXLog();
        openCrashHanlder();
        openLifeCycleHandler();
    }

    public void openLifeCycleHandler() {
        IActivityLifeCycle.Factory.get().init(sInstance);
    }

    public void openCrashHanlder() {
        SaltonCrashHandler.INSTANCE.init(true);
    }

    public void openXLog() {
        boolean hasPermission = checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Process.myPid(),
                Process.myUid()) == PackageManager.PERMISSION_GRANTED;
        XLog.config(new XLogConfig()
                .setWhetherToSaveLog(hasPermission)
                .setSavePath(C.BASE_PATH));
    }
}
