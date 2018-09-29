package com.salton123.saltonframeworkdemo.widget

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.salton123.saltonframeworkdemo.R

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/26 下午6:00
 * ModifyTime: 下午6:00
 * Description:
 */
class CoolToast(context: Context) : Toast(context) {

    companion object {
        @JvmOverloads
        @JvmStatic
        fun displayToast(context: Context,
                         text: CharSequence = "",
                         resId: Int = 0,
                         yOffset: Int = 0,
                         duration: Int = Toast.LENGTH_LONG
        ) {
            CoolToast(context).apply {
                text(text)
                icon(resId)
                yOffset(yOffset)
                setDuration(duration)
                balanceLayout()
                show()
            }
        }

    }

    init {
        setGravity(Gravity.CENTER, 0, 0)
        duration = Toast.LENGTH_LONG
        view = LayoutInflater.from(context).inflate(R.layout.cool_toast, null, false)
    }

    fun text(text: CharSequence) {
        if (!text.isNullOrEmpty()) {
            view.findViewById<TextView>(R.id.tvMsg).apply {
                this.text = text
                visibility = View.VISIBLE
            }
        }
    }

    fun icon(resId: Int) {
        if (resId > 0) {
            view.findViewById<ImageView>(R.id.ivIcon).apply {
                setImageResource(resId)
                visibility = View.VISIBLE
            }
        }

    }

    fun yOffset(offset: Int) {
        setGravity(Gravity.CENTER, 0, offset)
    }

    private fun balanceLayout() {
        view.findViewById<RelativeLayout>(R.id.rlRoot).apply {
            post {
                if (measuredWidth < measuredHeight) {
                    var params = LinearLayout.LayoutParams(measuredHeight, measuredHeight)
                    layoutParams = params
                    requestLayout()
                }
            }
        }
    }
}