package com.salton123.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.salton123.util.LogUtils
import com.salton123.util.ViewUtils
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by Administrator on 2017/6/6.
 */

abstract class BaseSupportActivity : SupportActivity(), IComponentLife {
    private lateinit var mContentView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        initVariable(savedInstanceState)
        super.onCreate(savedInstanceState)
        mContentView = inflater().inflate(getLayout(), null)
        setContentView(mContentView)
        initViewAndData()
        initListener()
    }

    override fun initListener() {

    }

    override fun context(): Context {
        return this
    }

    override fun log(msg: String) {
        LogUtils.d(msg)
    }

    override fun <VT : View> f(id: Int): VT {
        return ViewUtils.f(getRootView(), id)
    }

    override fun getRootView(): View {
        return mContentView
    }

    override fun longToast(toast: String) {
        Toast.makeText(applicationContext, toast, Toast.LENGTH_LONG).show()
    }

    override fun shortToast(toast: String) {
        Toast.makeText(applicationContext, toast, Toast.LENGTH_SHORT).show()
    }


    override fun inflater(): LayoutInflater {
        return LayoutInflater.from(this)
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        startActivity(Intent(this, clz).apply { bundle?.let { this@apply.putExtras(it) } })
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        startActivityForResult(Intent(this, clz).apply { bundle?.let { this@apply.putExtras(it) } }, requestCode)
    }
}
