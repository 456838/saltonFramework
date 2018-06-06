package com.salton123.util.asynctask

import android.os.HandlerThread

/**
 * 异步任务工具。
 */
class AsyncTaskLite(var threadName: String = "AsyncTask") {
    private val mTaskHandler by lazy { SafeDispatchHandler(mThread.looper) }
    private val mThread by lazy { HandlerThread(threadName) }

    init {
        mThread.start()
    }

    /**
     * 延迟执行任务，单位milliseconds
     */
    fun scheduledDelayed(command: Runnable, delay: Long): Boolean {
        removeCallbacks(command)
        return mTaskHandler.postDelayed(command, delay)
    }

    /**
     * cancle
     */
    fun removeCallbacks(command: Runnable) {
        mTaskHandler.removeCallbacks(command)
    }
}
