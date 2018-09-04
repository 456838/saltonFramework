package com.salton123.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

/**
 * Created by Administrator on 2017/6/6.
 */

abstract class BaseSupportActivity : SupportActivity(), IComponentLife {
    private val mActivityDelegate by lazy { ActivityDelegate(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        mActivityDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(mActivityDelegate.onCreateView())
        mActivityDelegate.onViewCreated()
        fragmentAnimator = DefaultHorizontalAnimator()
    }

    override fun initListener() {

    }

    override fun setListener(vararg ids: Int) {
        mActivityDelegate.setListener(*ids)
    }

    override fun setListener(vararg views: View) {
        mActivityDelegate.setListener(*views)
    }

    override fun activity(): AppCompatActivity {
        return mActivityDelegate.activity()
    }

    override fun onClick(v: View?) {

    }

    override fun log(msg: String) {
        mActivityDelegate.log(msg)
    }

    override fun <VT : View> f(id: Int): VT {
        return mActivityDelegate.f(id)
    }

    override fun getRootView(): View {
        return mActivityDelegate.getRootView()
    }

    override fun longToast(toast: String) {
        mActivityDelegate.longToast(toast)
    }

    override fun shortToast(toast: String) {
        mActivityDelegate.shortToast(toast)
    }


    override fun inflater(): LayoutInflater {
        return mActivityDelegate.inflater()
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mActivityDelegate.openActivity(clz, bundle)
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mActivityDelegate.openActivityForResult(clz, bundle, requestCode)
    }

    override fun onDestroy() {
        mActivityDelegate.onDestroy()
        super.onDestroy()
    }
}
