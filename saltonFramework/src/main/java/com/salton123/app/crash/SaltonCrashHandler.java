package com.salton123.app.crash;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.salton123.app.BaseApplication;
import com.salton123.util.ThrowableUtils;
import com.salton123.io.FlushWriter;
import com.salton123.log.XLog;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/9 18:12
 * ModifyTime: 18:12
 * Description:
 */
public enum SaltonCrashHandler implements Thread.UncaughtExceptionHandler {
    INSTANCE;

    private Thread.UncaughtExceptionHandler mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

    private boolean isShowCrashPanel;


    public void init(boolean showCrashPanel) {
        isShowCrashPanel = showCrashPanel;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(t, e)) {
            mDefaultHandler.uncaughtException(t, e);
        }
    }

    private boolean handleException(Thread thread, Throwable throwable) {
        String path = new File(Environment.getExternalStorageDirectory(), "salton").getPath();
        path = path + File.separator + BaseApplication.getInstance().getPackageName();
        String crashPath = path + File.separator + createFile();
        FlushWriter flush = new FlushWriter(path + File.separator + "crash_buf",
                8192 * 4,
                crashPath
                , false
        );
        StringBuilder stringBuilder = new StringBuilder();
        flush.changeLogPath(crashPath);
        stringBuilder.append(collectDeviceInfo() + "\n")
                .append(ThrowableUtils.getFullStackTrace(throwable) + "\n");
        flush.write(stringBuilder.toString());
        flush.flushAsync();
        flush.release();
        if (isShowCrashPanel) {
            Intent intent = new Intent(BaseApplication.getInstance(), CrashService.class);
            intent.putExtra(CrashPanelAty.FLAG_INFO, stringBuilder.toString());
            BaseApplication.getInstance().startService(intent);
        }
        return false;
    }

    private String createFile() {
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
        String time = formatter.format(new Date());
        return "crash_" + time + ".txt";
    }

    String collectDeviceInfo() {
        StringBuffer sb = new StringBuffer();
        try {
            PackageManager pm = BaseApplication.getInstance().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(BaseApplication.getInstance().getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                int versionCode = pi.versionCode;
                sb.append("versionName:" + versionName + "\n");
                sb.append("versionCode:" + versionCode + "\n");
            }
        } catch (Exception e) {
            XLog.e(this, "an error occured when collect package info:" + e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                sb.append(field.getName() + ":" + field.get(null) + "\n");
            } catch (Exception e) {
            }
        }
        return sb.toString();
    }
}
