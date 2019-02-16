package com.zhenai.crashpanel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.salton123.saltonframework.R
import kotlinx.android.synthetic.main.salton_aty_crash_panel.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/11 11:30 AM
 * ModifyTime: 11:30 AM
 * Description:
 */
class CrashPanelAty : Activity() {
    companion object {
        val FLAG_INFO = "info"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.salton_aty_crash_panel)
        updateLogic()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        updateLogic()
    }

    private fun updateLogic() {
        val info = intent.getStringExtra(FLAG_INFO)
        tvCrashInfo.text = info
        btnShare.setOnClickListener {
            share(this@CrashPanelAty, tvCrashInfo.text.toString())
        }
    }

    fun share(context: Context, extraText: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/*"
        intent.putExtra(Intent.EXTRA_SUBJECT, "崩溃")
        intent.putExtra(Intent.EXTRA_TEXT, extraText)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(
            Intent.createChooser(intent, "分享"))
    }

}