package com.salton123.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/6/14 19:18
 * Time: 19:18
 * Description:
 */
abstract class ActivityBase : AppCompatActivity(), IComponentLife {
    private val mActivityDelegate by lazy { ActivityDelegate(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        mActivityDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(mActivityDelegate.onCreateView())
        mActivityDelegate.onViewCreated()
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