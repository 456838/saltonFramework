package com.salton123.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/3 下午1:59
 * ModifyTime: 下午1:59
 * Description:
 */
class ActivityDelegate(componentLife: IComponentLife) : LifeDelegate(componentLife) {

    override fun activity(): AppCompatActivity = mHost

    private var mHost: AppCompatActivity

    init {
        if (mComponentLife !is AppCompatActivity) {
            throw RuntimeException("instance must AppCompatActivity")
        }
        mHost = componentLife as AppCompatActivity
    }

    fun openActivity(clz: Class<*>) {
        activity().startActivity(Intent(mComponentLife.activity(), clz))
    }

    fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mHost.startActivity(Intent(mComponentLife.activity(), clz).let { it.putExtras(bundle) })
    }

    fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mHost.startActivityForResult(Intent(mComponentLife.activity(), clz).let { it.putExtras(bundle) }, requestCode)
    }


}