package com.salton123.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.salton123.app.BaseCrashHandler;
import com.salton123.app.CrashHandler;
import com.salton123.app.IFutureTaskPriority;
import com.salton123.app.InitializeLoader;
import com.salton123.app.Loader;
import com.salton123.app.RebootThreadExceptionHandler;
import com.salton123.app.SaltonCrashHandler;
import com.salton123.log.LogConfiguration;
import com.salton123.log.LogLevel;
import com.salton123.log.XLog;
import com.salton123.log.flattener.ClassicFlattener;
import com.salton123.log.interceptor.BlacklistTagsFilterInterceptor;
import com.salton123.log.printer.AndroidPrinter;
import com.salton123.log.printer.Printer;
import com.salton123.log.printer.file.FilePrinter;
import com.salton123.log.printer.file.naming.DateFileNameGenerator;
import com.salton123.manager.lifecycle.IActivityLifeCycle;
import com.salton123.saltonframework.BuildConfig;
import com.salton123.saltonframework.R;
import com.salton123.task.AbsTask;
import com.salton123.task.Callback;
import com.salton123.task.TaskController;
import com.salton123.task.TaskControllerImpl;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2015-08-06
 * Time: 12:23
 * Desc:捕获应用异常Application以及完成整个应用退出；在这里进行全局变量的传递；在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置, 预加载处理
 */


public class ApplicationBase extends Application {
    public static ApplicationBase mInstance;

    public static <T extends ApplicationBase> T getInstance() {
        return (T) mInstance;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        // InitializeLoader.INSTANCE.init();
        // openCrashHanlder();
        // initXlog();
        InitializeLoader.INSTANCE.init(new IFutureTaskPriority() {
            @Override
            public boolean lowPriority() {

                openCrashHanlder();
                initXlog();
                Log.e("newsalton", "lowPriority:" + Thread.currentThread());
                return true;
            }

            @Override
            public boolean mediumPriority() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                IActivityLifeCycle.Factory.get().init(mInstance);
                return true;
            }

            @Override
            public boolean highPriority() {
                return true;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
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
        // CrashHandler handler = CrashHandler.getInstance();
        // handler.init(this);
        SaltonCrashHandler handler1 = SaltonCrashHandler.INSTANCE;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        IActivityLifeCycle.Factory.get().unInit();
    }

    // 在内存低时,发送广播可以释放一些内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 退出App
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * Initialize XLog.
     */
    private void initXlog() {
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(isLogDebug() ? LogLevel.ALL             // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
                        : LogLevel.NONE)
                .tag("salton")                   // Specify TAG, default: "X-LOG"
                .st(2)
                .build();
        String path = new File(Environment.getExternalStorageDirectory(), "salton").getPath();
        path = path + File.separator + getPackageName();
        Printer androidPrinter = new AndroidPrinter();             // Printer that print the log using android.util.Log
        Printer filePrinter = new FilePrinter                      // Printer that print the log to the file system
                .Builder(path)       // Specify the path to save log file
                .fileNameGenerator(new DateFileNameGenerator())        // Default: ChangelessFileNameGenerator("log")
                .logFlattener(new ClassicFlattener())                  // Default: DefaultFlattener
                .build();

        XLog.init(                                                 // Initialize XLog
                config,                                                // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
                androidPrinter,                                        // Specify printers, if no printer is specified, AndroidPrinter(for Android)/ConsolePrinter(for java) will be used.
                filePrinter);
    }

    public boolean isLogDebug() {
        return true;
    }

}
