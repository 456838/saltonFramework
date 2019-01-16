package com.salton123.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/14 16:57
 * ModifyTime: 16:57
 * Description: Aty和Fm通用接口
 */
interface IComponentLife : View.OnClickListener {
    fun getLayout(): Int    //当前布局

    fun getRootView(): View

    fun getTitleBar(): View?

    fun initVariable(savedInstanceState: Bundle?)    //在setContentView之前初始化数据

    fun initViewAndData()  //初始化数据在View之后

    fun initListener()     //监听回调

    fun longToast(toast: String)    //长Toast

    fun shortToast(toast: String)   //短Toast

    fun activity(): AppCompatActivity

    fun log(msg: String)

    fun inflater(): LayoutInflater

    fun <VT : View> f(@IdRes id: Int): VT

    fun openActivity(clz: Class<*>, bundle: Bundle? = Bundle.EMPTY)

    fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int)

    fun setListener(vararg ids: Int)

    fun setListener(vararg views: View)
}
