package com.salton123.saltonframeworkdemo;

import android.net.Uri;
import android.os.Environment;

import com.salton123.log.XLog;

import java.io.File;

/**
 * Created by salton on 2017/12/3.
 */

public class UriProvider {

    private static final String TAG = "UriProvider";

    //资源放在/sdcard/leland/res
    public UriProvider() {

    }

    public static String defaultPath() {
        return Environment.getExternalStorageDirectory() + File.separator + "ec" + File.separator + "res";
//        return Environment.getExternalStorageDirectory() +"";
    }

    public static String getVideoPath(String resNameWithSuffix) {
        return defaultPath() + File.separator + resNameWithSuffix;
    }

    private static Uri getVideoPathUriFromLocal(String resNameWithSuffix) {
        return Uri.parse(getVideoPath(resNameWithSuffix));
    }

    public static Uri getVideoPathUriFromRaw(String resNameWithSuffix) {
        return Uri.parse(getVideoPathWithoutSuffix(resNameWithSuffix));
    }

    public static String getVideoPathWithoutSuffix(String resNameWithSuffix) {
        String packageName = SaltonBaseApplication.getInstance().getPackageName();
        String resName = resNameWithSuffix.substring(0, resNameWithSuffix.lastIndexOf("."));
        int resId = SaltonBaseApplication.getInstance().getResources().getIdentifier(resName, "raw", packageName);
        XLog.i(TAG, "[getVideoPathWithoutSuffix] uri=" + "android.resource://" + packageName + "/" + resId);
        return "android.resource://" + packageName + "/" + resId;//*R.raw.handle_02*//*;
    }


    public static Uri getVideoPathUri(String resNameWithSuffix) {
        return getVideoPathUriFromRaw(resNameWithSuffix);
        // if (BuildConfig.PACKAGE_TYPE == 2) {
        //     return getVideoPathUriFromRaw(resNameWithSuffix);
        // } else {
        //     return getVideoPathUriFromLocal(resNameWithSuffix);
        // }
    }
}
