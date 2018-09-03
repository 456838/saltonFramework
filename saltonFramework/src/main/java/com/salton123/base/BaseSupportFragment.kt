package com.salton123.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment

/**
 * User: 巫金生(newSalton@163.com)
 * Date: 2017/6/6 23:45
 * Description:
 * Updated:
 */
abstract class BaseSupportFragment : SupportFragment(), IComponentLife {
    private val mFragmentDelegate by lazy { FragmentDelegate(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentDelegate.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mFragmentDelegate.onCreateView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentDelegate.onViewCreated()
    }

    override fun onDestroy() {
        mFragmentDelegate.onDestroy()
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
        mFragmentDelegate.log(msg)
    }


    override fun longToast(toast: String) {
        mFragmentDelegate.longToast(toast)
    }

    override fun shortToast(toast: String) {
        mFragmentDelegate.shortToast(toast)
    }

    override fun <VT : View> f(id: Int): VT {
        return mFragmentDelegate.f(id)
    }

    override fun getRootView(): View {
        return mFragmentDelegate.getRootView()
    }

    override fun inflater(): LayoutInflater {
        return mFragmentDelegate.inflater()
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mFragmentDelegate.openActivity(clz, bundle)
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mFragmentDelegate.openActivityForResult(clz, bundle, requestCode)
    }
}
