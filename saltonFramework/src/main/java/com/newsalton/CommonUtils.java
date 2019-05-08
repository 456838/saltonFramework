package com.newsalton;

import com.salton123.app.BaseApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * User: newSalton@outlook.com
 * Date: 2019-05-09 00:08
 * ModifyTime: 00:08
 * Description:
 */
public class CommonUtils {
    /**
     * Return the name of current process.
     * <p>It's faster than ActivityManager.</p>
     *
     * @return the name of current process
     */
    public static String getCurrentProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return whether app running in the main process.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isMainProcess() {
        return BaseApplication.getInstance().getPackageName().equals(getCurrentProcessName());
    }
}
