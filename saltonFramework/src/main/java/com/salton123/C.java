package com.salton123;

import android.os.Environment;

import com.salton123.app.BaseApplication;

import java.io.File;

/**
 * User: newSalton@outlook.com
 * Date: 2019-05-09 23:42
 * ModifyTime: 23:42
 * Description:
 */
public class C {
    public static String BASE_PATH = new File(Environment.getExternalStorageDirectory(), "salton").getPath()
            + File.separator + BaseApplication.getInstance().getPackageName();
}
