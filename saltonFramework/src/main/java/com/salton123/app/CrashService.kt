package com.zhenai.crashpanel

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import com.zhenai.crashpanel.CrashPanelAty.Companion.FLAG_INFO

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/11 4:59 PM
 * ModifyTime: 4:59 PM
 * Description:奇怪的魅族的手机崩溃的时候直接调用startActivity会出现卡死，因此放到service里
 */
class CrashService : IntentService("CrashService") {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onHandleIntent(intent: Intent?) {
        val crashInfo = intent!!.getStringExtra(FLAG_INFO)
        val crashIntent = Intent(application, CrashPanelAty::class.java)
        crashIntent.putExtra(FLAG_INFO, crashInfo)
        crashIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(crashIntent)
    }
}
