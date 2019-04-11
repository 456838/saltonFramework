package com.salton123.saltonframeworkdemo

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import com.salton123.base.BaseActivity
import com.salton123.saltonframeworkdemo.mvp.MvpTestFragment
import com.salton123.util.FragmentUtils

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/14 18:48
 * ModifyTime: 18:48
 * Description:
 */
class KotlinAty : BaseActivity() {
    override fun getLayout(): Int {
        return R.layout.aty_kotlin
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        FragmentUtils.newInstance(MvpTestFragment::class.java)
    }

    override fun initViewAndData() {
        var imageView = ImageView(activity())
        imageView.setImageResource(R.mipmap.ic_launcher_round)
        (getRootView() as ViewGroup).addView(imageView)
        openActivity(MainActivity::class.java, null)

    }
}