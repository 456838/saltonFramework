package com.salton123.util;
import com.salton123.log.XLog;

@Deprecated
public class MLog {

    public static void verbose(String format, Object... args) {
        XLog.v(format,args);
    }

    public static void debug(String format, Object... args) {
        XLog.d(format,args);
    }

    public static void info(String format, Object... args) {
        XLog.i(format,args);
    }

    public static void warn(String format, Object... args) {
        XLog.w(format,args);
    }

    public static void error(String format, Object... args) {
        XLog.e(format,args);
    }

    public static void verbose(String format) {
        XLog.v(format);
    }

    public static void v(String format) {
        XLog.v(format);
    }

    public static void debug(String format) {
        XLog.d(format);
    }

    public static void d(String format) {
        XLog.d(format);
    }

    public static void info(String format) {
        XLog.i(format);
    }

    public static void i(String format) {
        XLog.i(format);
    }

    public static void warn(String format) {
        XLog.w(format);
    }

    public static void w(String format) {
        XLog.w(format);
    }

    public static void error(String format) {
        XLog.e(format);
    }

    public static void e(String format) {
        XLog.e(format);
    }
}
