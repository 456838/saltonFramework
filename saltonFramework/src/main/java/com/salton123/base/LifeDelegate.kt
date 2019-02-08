package com.salton123.base

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import com.salton123.log.XLog
import com.salton123.saltonframework.R
import com.salton123.util.ViewUtils

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/3 下午2:15
 * ModifyTime: 下午2:15
 * Description:
 */
abstract class LifeDelegate(var mComponentLife: IComponentLife) {
    private lateinit var rootView: ViewGroup

    fun onCreate(@Nullable savedInstanceState: Bundle?) {
        mComponentLife.initVariable(savedInstanceState)
    }

    fun onCreateView(): View? {
        rootView = buildRootView()
        return rootView
    }

    private fun buildRootView(): ViewGroup {
        val mainContentView = inflater().inflate(mComponentLife.getLayout(), null)
        if (getTitleBar() != null) {
            var topLayout = LinearLayout(activity())
            topLayout.id = R.id.salton_id_top_layout
            topLayout.orientation = LinearLayout.VERTICAL
            topLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            var titleLayout = LinearLayout(activity())
            titleLayout.id = R.id.salton_id_title_layout
            titleLayout.orientation = LinearLayout.VERTICAL
            titleLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            titleLayout.addView(getTitleBar())
            topLayout.addView(titleLayout)

            var contentLayout = FrameLayout(activity())
            contentLayout.id = R.id.salton_id_content_layout
            contentLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            contentLayout.addView(mainContentView)
            topLayout.addView(contentLayout)
            
            return topLayout
        } else {
            return mainContentView as ViewGroup
        }

    }

    fun getTitleBar(): View? {
        return mComponentLife.getTitleBar()
    }

    fun onViewCreated() {
        mComponentLife.initViewAndData()
        mComponentLife.initListener()
    }

    fun log(msg: String) {
        XLog.i(this, msg)
    }

    fun longToast(toast: String) {
        Toast.makeText(mComponentLife.activity(), toast, Toast.LENGTH_LONG).show()
    }

    fun shortToast(toast: String) {
        Toast.makeText(mComponentLife.activity(), toast, Toast.LENGTH_SHORT).show()
    }

    fun <VT : View> f(id: Int): VT {
        return ViewUtils.f(rootView, id)
    }

    fun getRootView(): View {
        return rootView
    }

    fun inflater(): LayoutInflater {
        return LayoutInflater.from(mComponentLife.activity())
    }

    abstract fun activity(): AppCompatActivity

    fun setListener(vararg ids: Int) {
        for (id in ids) {
            f<View>(id).setOnClickListener(mComponentLife)
        }
    }

    open fun setListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(mComponentLife)
        }
    }

    fun onDestroy() {}

}