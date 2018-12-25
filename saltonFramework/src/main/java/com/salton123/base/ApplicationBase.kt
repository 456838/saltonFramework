package com.salton123.base

import android.app.Application
import android.os.Environment
import android.util.Log
import com.salton123.app.IFutureTaskPriority
import com.salton123.app.InitializeLoader
import com.salton123.app.SaltonCrashHandler
import com.salton123.log.XLog
import com.salton123.log.XLogConfig
import com.salton123.log.printer.FilePrinter
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

    override fun onTerminate() {
        super.onTerminate()
        IActivityLifeCycle.Factory.get().unInit()
        System.gc()
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
        var path = File(Environment.getExternalStorageDirectory(), "salton").path
        path = path + File.separator + ApplicationBase.mInstance.packageName
        XLogConfig.init(XLogConfig.Builder()
            .setSavePath(path)
            .build())
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
