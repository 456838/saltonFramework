package com.salton123

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.ScrollView
import com.salton123.base.ActivityBase
import com.salton123.saltonframework.R
import com.salton123.util.BitmapUtil
import kotlinx.android.synthetic.main.salton_aty_crash_panel.*
import java.io.File

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/11 11:30 AM
 * ModifyTime: 11:30 AM
 * Description:
 */
class CrashPanelAty : ActivityBase() {
    override fun getLayout(): Int = R.layout.salton_aty_crash_panel

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        longToast("发生崩溃了")
        tvCrashInfo.text = intent.getStringExtra("crashInfo")
        tvCrashInfo.post {
            BitmapUtil.saveBitmap(getScrollViewBitmap(scrollView), File("/sdcard/"))
        }
    }

    fun getScrollViewBitmap(scrollView: ScrollView): Bitmap {
        var h = 0
        val bitmap: Bitmap
        for (i in 0 until scrollView.childCount) {
            h += scrollView.getChildAt(i).height
        }        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.width, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        scrollView.draw(canvas)
        return bitmap
    }


}