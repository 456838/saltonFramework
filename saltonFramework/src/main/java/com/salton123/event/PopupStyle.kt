package com.salton123.event

import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.WindowManager
import com.salton123.saltonframework.R

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/1 下午6:18
 * ModifyTime: 下午6:18
 * Description:
 */
data class PopupStyle(
    var style: Int = DialogFragment.STYLE_NORMAL,
    var theme: Int = R.style.salton_full_screen_dialog,
    var anim: Int = R.style.salton_dialog_horizontal_anim,
    var canceledOnTouchOutside: Boolean = true,
    var backgroundDrawableResource: Int = android.R.color.transparent,
    var layoutWidth: Int = WindowManager.LayoutParams.MATCH_PARENT,
    var layoutHeight: Int = WindowManager.LayoutParams.MATCH_PARENT,
    var gravity: Int = Gravity.CENTER
)