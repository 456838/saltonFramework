package com.za.youth

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.util.Log
import com.salton123.app.FutureTaskLoader
import com.salton123.app.IFutureTaskPriority
import java.util.concurrent.CountDownLatch

/**
 * User: newSalton@outlook.com
 * Date: 2018/12/26 11:39 AM
 * ModifyTime: 11:39 AM
 * Description: 每个进程都会执行Application的初始化操作，对此我们需要在主进程区别初始化
 */
open class FutureTaskApplication : Application(), IFutureTaskPriority {
    private val mCountDownLatch = CountDownLatch(1)
    private val TAG = "InitializeLoader"

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "step one:" + Thread.currentThread())
        if (isMainProcessType()) {
            runOnMainProcessMainThread()
            FutureTaskLoader.init(this, mCountDownLatch)
            mCountDownLatch.await()
            Log.d(TAG, "step three:" + Thread.currentThread().name)
        }
        runOnAllProcessMainThread()
    }

    open fun runOnAllProcessMainThread() {

    }

    open fun runOnMainProcessMainThread() {

    }


    fun isMainProcessType(): Boolean {
        val processName = getCurrentProcessName()
        return processName != null && processName == packageName
    }

    fun getCurrentProcessName(): String? {
        val pid = android.os.Process.myPid()
        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager ?: return null
        val infoList = am.runningAppProcesses
        if (infoList != null && infoList.size > 0) {
            for (appProcess in infoList) {
                if (appProcess.pid == pid) {
                    return appProcess.processName
                }
            }
        }
        return null
    }

    override fun highPriority(): Boolean {
        Log.d(TAG, "step two high:" + Thread.currentThread().name)
        return true
    }

    override fun mediumPriority(): Boolean {
        Log.d(TAG, "step two medium:" + Thread.currentThread().name)
        return true
    }

    override fun lowPriority(): Boolean {
        Log.d(TAG, "step two low:" + Thread.currentThread().name)
        return true
    }

}
