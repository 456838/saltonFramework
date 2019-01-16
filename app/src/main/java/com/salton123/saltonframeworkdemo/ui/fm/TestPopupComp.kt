package com.salton123.saltonframeworkdemo.ui.fm

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.salton123.base.BaseSupportPopupFragment
import com.salton123.saltonframeworkdemo.R
import com.salton123.util.BitmapUtil
import kotlinx.android.synthetic.main.cp_popup_test.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/1 下午6:59
 * ModifyTime: 下午6:59
 * Description:
 */
class TestPopupComp : BaseSupportPopupFragment() {
    override fun getLayout(): Int = R.layout.cp_popup_test

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        view?.post {
            ivBlur.setImageBitmap(buildBlurView(ivBlur, 35))
        }
    }

    fun buildBlurView(view: View, radius: Int): Bitmap {
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        var bitmap = Bitmap.createBitmap(view.drawingCache)
        return BitmapUtil.blur(view.context, bitmap, radius)
    }

    override fun getTitleBar(): View? {
        return inflater().inflate(R.layout.title_bar, null)
    }
}