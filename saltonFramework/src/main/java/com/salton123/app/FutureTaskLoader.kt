package com.salton123.app

import android.util.Log
import java.util.concurrent.Callable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.FutureTask


object FutureTaskLoader {
    private val TAG = "FutureTaskLoader"

    private lateinit var mPriority: IFutureTaskPriority
    private lateinit var mLatch: CountDownLatch //定义一个CountDownLatch只保证高优先级初始化操作执行完后主线程继续执行
    private val FUTURE_PRIORITY_HIGH = 10
    private val FUTURE_PRIORITY_MEDIUM = 9
    private val FUTURE_PRIORITY_LOW = 8

    fun init(priority: IFutureTaskPriority, latch: CountDownLatch) {
        mPriority = priority
        mLatch = latch
        Log.e(TAG, "highPriority start:" + Thread.currentThread())
        futrueTask(FUTURE_PRIORITY_HIGH, mPriority::highPriority, latch)
        Log.e(TAG, "highPriority end:" + Thread.currentThread())
        futrueTask(FUTURE_PRIORITY_MEDIUM, mPriority::mediumPriority,latch)
        Log.e(TAG, "mediumPriority end:" + Thread.currentThread())
        futrueTask(FUTURE_PRIORITY_LOW, mPriority::lowPriority)
        Log.e(TAG, "lowPriority end:" + Thread.currentThread())
    }

    /**
     * TODO range 注解设置取值范围为0-10
     */
    private fun futrueTask(priority: Int, method: () -> Boolean, latch: CountDownLatch? = null) {
        val futrueTask = FutureTask<Boolean>(Callable<Boolean> {
            var ret = method.invoke()
            latch?.countDown()
            ret
        })
        var priorityThread = Thread(futrueTask, "future-$priority")
        priorityThread.priority = priority
        priorityThread.start()
    }

}
