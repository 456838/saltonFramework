package com.salton123.util;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

public class MLog {

    public static void init(){
        Logger.addLogAdapter(new DiskLogAdapter());
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
    public static void verbose(String format, Object... args) {
        Logger.v(format, args);
    }

    public static void debug(String format, Object... args) {
        Logger.d(format,args);
    }

    public static void info(String format, Object... args) {
        Logger.i(format, args);
    }

    public static void warn(String format, Object... args) {
        Logger.w(format, args);
    }

    public static void error(String format, Object... args) {
        Logger.e(format, args);
    }
}
