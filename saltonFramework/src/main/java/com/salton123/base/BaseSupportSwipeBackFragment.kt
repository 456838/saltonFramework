package com.salton123.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment

/**
 * User: 巫金生(newSalton@163.com)
 * Date: 2017/6/11 10:50
 * Description: 必须要在onViewCreated中return attachToSwipeBack(mContentView);
 * Updated:
 */
abstract class BaseSupportSwipeBackFragment : SwipeBackFragment(), IComponentLife {

    private lateinit var mDelegate: FragmentDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate = FragmentDelegate(this)
        mDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setParallaxOffset(0.5f)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mDelegate.onCreateView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDelegate.onViewCreated()
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        super.onDestroy()
    }

    override fun initListener() {

    }

    override fun setListener(vararg ids: Int) {
        for (id in ids) {
            f<View>(id).setOnClickListener(this)
        }
    }

    override fun setListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(this)
        }
    }

    override fun activity(): AppCompatActivity {
        return this.activity as AppCompatActivity
    }

    override fun onClick(v: View?) {

    }

    override fun log(msg: String) {
        mDelegate.log(msg)
    }


    override fun longToast(toast: String) {
        mDelegate.longToast(toast)
    }

    override fun shortToast(toast: String) {
        mDelegate.shortToast(toast)
    }

    override fun <VT : View> f(id: Int): VT {
        return mDelegate.f(id)
    }

    override fun getRootView(): View {
        return mDelegate.getRootView()
    }

    override fun inflater(): LayoutInflater {
        return mDelegate.inflater()
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mDelegate.openActivity(clz, bundle)
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mDelegate.openActivityForResult(clz, bundle, requestCode)
    }

}

