package com.salton123.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.os.Process
import android.util.Log
import com.salton123.log.XLogConfig
import com.salton123.model.manager.lifecycle.IActivityLifeCycle
import java.io.File


/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2015-08-06
 * Time: 12:23
 * Desc:捕获应用异常Application以及完成整个应用退出；在这里进行全局变量的传递；
 * 在这里完成低内存的释放；在这里捕获未抓住的异常；用于应用配置, 预加载处理
 */
open class BaseApplication : FutureTaskApplication() {
    val TAG = "BaseApplication"
    override fun onCreate() {
        super.onCreate()
        sInstance = this
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
        IActivityLifeCycle.Factory.get().init(sInstance)
        return true
    }

    override fun lowPriority(): Boolean {
        Log.e(TAG, "step two low:" + Thread.currentThread().name)
        openCrashHanlder()
        initXlog()
        return true
    }

    /**
     * 异常处理收集
     */
    open fun openCrashHanlder() {
        SaltonCrashHandler.isShowCrashPanel = false
    }

    /**
     * Initialize XLog.
     */
    private fun initXlog() {
        var path = File(Environment.getExternalStorageDirectory(), "salton").path
        var hasPermission = checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Process.myPid(),
            Process.myUid()) == PackageManager.PERMISSION_GRANTED
        path = path + File.separator + sInstance.packageName
        XLogConfig.init(XLogConfig.Builder()
            .whetherToSaveLog(hasPermission)
            .setSavePath(path)
            .build())
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        private lateinit var sInstance: BaseApplication
        @JvmStatic
        fun <T : BaseApplication> getInstance(): T {
            return sInstance as T
        }
    }
}
