package com.salton123.base.bus

import com.salton123.log.XLog
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Logger
import java.util.logging.Level

/**
 * User: newSalton@outlook.com
 * Date: 2018/12/28 5:57 PM
 * ModifyTime: 5:57 PM
 * Description:
 */
val TAG = "EventFactory"

object EventFactory {
    private val mEventBus by lazy {
        EventBus.builder()
                .logger(object : Logger {
                    override fun log(level: Level?, msg: String?) {
                        XLog.d(TAG, "level:$level,msg:$msg")
                    }

                    override fun log(level: Level?, msg: String?, th: Throwable?) {
                        XLog.d(TAG, "level:$level,msg:$msg,ex:${th.toString()}")
                    }
                })
                .build()
    }

    @JvmStatic
    fun register(context: Any) {
        if (!mEventBus.isRegistered(context)) {
            mEventBus.register(context)
        }
    }

    @JvmStatic
    fun unregister(context: Any) {
        if (mEventBus.isRegistered(context)) {
            mEventBus.unregister(context)
        }
    }

    @JvmStatic
    fun sendEvent(obj: Any) {
        mEventBus.post(obj)
    }
}