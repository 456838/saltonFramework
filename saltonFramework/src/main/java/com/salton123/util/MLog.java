package com.salton123.util;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

public class MLog {

    public static void init() {
        Logger.addLogAdapter(new DiskLogAdapter());
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
    public static void verbose(String format, Object... args) {
        Logger.v(format);
    }

    public static void debug(String format, Object... args) {
        Logger.d(format);
    }

    public static void info(String format, Object... args) {
        Logger.i(format);
    }

    public static void warn(String format, Object... args) {
        Logger.w(format);
    }

    public static void error(String format, Object... args) {
        Logger.e(format);
    }

    public static void verbose(String format) {
        Logger.v(format);
    }

    public static void v(String format) {
        Logger.v(format);
    }

    public static void debug(String format) {
        Logger.d(format);
    }

    public static void d(String format) {
        Logger.d(format);
    }

    public static void info(String format) {
        Logger.i(format);
    }

    public static void i(String format) {
        Logger.i(format);
    }

    public static void warn(String format) {
        Logger.w(format);
    }

    public static void w(String format) {
        Logger.w(format);
    }

    public static void error(String format) {
        Logger.e(format);
    }

    public static void e(String format) {
        Logger.e(format);
    }
}
