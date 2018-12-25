package com.salton123.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.salton123.util.ViewUtils

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/3 下午2:15
 * ModifyTime: 下午2:15
 * Description:
 */
abstract class LifeDelegate(var mComponentLife: IComponentLife) {
    private lateinit var rootView: View
    fun onCreate(savedInstanceState: Bundle?) {
        mComponentLife.initVariable(savedInstanceState)
    }

    fun onCreateView(): View? {
        rootView = inflater().inflate(mComponentLife.getLayout(), null)
        return rootView
    }

    fun onViewCreated() {
        mComponentLife.initViewAndData()
        mComponentLife.initListener()
    }

    fun log(msg: String) {
        LogUtils.d(msg)
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