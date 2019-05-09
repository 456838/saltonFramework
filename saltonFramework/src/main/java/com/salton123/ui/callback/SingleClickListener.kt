package com.salton123.ui.callback

import android.os.SystemClock
import android.view.View

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/20 下午2:55
 * ModifyTime: 下午2:55
 * Description:
 */
abstract class SingleClickListener(var interval: Int = 1000) : View.OnClickListener {
    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
        val currentTime = SystemClock.elapsedRealtime()
        val delayTime = currentTime - lastClickTime
        if (delayTime > interval) {
            onSingleClick(v)
            lastClickTime = currentTime
        } else {
            onDoubleClick(v)
        }
    }

    open fun onDoubleClick(v: View) {

    }

    abstract fun onSingleClick(v: View)
}
