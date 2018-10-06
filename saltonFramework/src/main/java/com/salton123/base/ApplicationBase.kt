package com.salton123.base

import android.app.Application
import android.os.Environment
import android.util.Log
import com.salton123.app.IFutureTaskPriority
import com.salton123.app.InitializeLoader
import com.salton123.app.RebootThreadExceptionHandler
import com.salton123.app.SaltonCrashHandler
import com.salton123.log.LogConfiguration
import com.salton123.log.LogLevel
import com.salton123.log.XLog
import com.salton123.log.flattener.ClassicFlattener
import com.salton123.log.printer.AndroidPrinter
import com.salton123.log.printer.file.FilePrinter
import com.salton123.log.printer.file.naming.DateFileNameGenerator
import com.salton123.manager.lifecycle.IActivityLifeCycle
import java.io.File


/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2015-08-06
 * Time: 12:23
 * Desc:捕获应用异常Application以及完成整个应用退出；在这里进行全局变量的传递；在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置, 预加载处理
 */
open class ApplicationBase : Application(), IFutureTaskPriority {
    var isLogDebug: Boolean = true
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        InitializeLoader.init(this).subscribe()
    }

    /**
     * 程序异常关闭1s之后重新启动
     */
    fun openRestartHandler() {
        RebootThreadExceptionHandler(baseContext)
    }


    override fun onTerminate() {
        super.onTerminate()
        IActivityLifeCycle.Factory.get().unInit()
        System.gc()
    }

    /**
     * 退出App
     */
    fun exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    override fun highPriority(): Boolean {
        return true
    }

    override fun mediumPriority(): Boolean {
        IActivityLifeCycle.Factory.get().init(mInstance)
        return true
    }

    override fun lowPriority(): Boolean {
        openCrashHanlder()
        initXlog()
        Log.e("newsalton", "lowPriority:" + Thread.currentThread())
        return true
    }

    /**
     * 异常处理收集
     */
    open fun openCrashHanlder() {
        SaltonCrashHandler
    }

    /**
     * Initialize XLog.
     */
    private fun initXlog() {
        val config = LogConfiguration.Builder()
            .logLevel(if (isLogDebug)
                LogLevel.ALL             // Specify log level, logs below this level won't be printed, default: LogLevel.ALL
            else
                LogLevel.NONE)
            .tag("salton")                   // Specify TAG, default: "X-LOG"
            .st(2)
            .build()
        var path = File(Environment.getExternalStorageDirectory(), "salton").path
        path = path + File.separator + ApplicationBase.mInstance.packageName
        val androidPrinter = AndroidPrinter()             // Printer that print the log using android.util.Log
        val filePrinter = FilePrinter.Builder(path)     // Specify the path to save log file
            .fileNameGenerator(DateFileNameGenerator())        // Default: ChangelessFileNameGenerator("log")
            .logFlattener(ClassicFlattener())                  // Default: DefaultFlattener
            .build()

        XLog.init(                                                 // Initialize XLog
            config, // Specify the log configuration, if not specified, will use new LogConfiguration.Builder().build()
            androidPrinter, // Specify printers, if no printer is specified, AndroidPrinter(for Android)/ConsolePrinter(for java) will be used.
            filePrinter)
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        private lateinit var mInstance: ApplicationBase

        @JvmStatic
        fun <T : ApplicationBase> getInstance(): T {
            return mInstance as T
        }
    }
}
