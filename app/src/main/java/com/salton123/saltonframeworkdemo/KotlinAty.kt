package com.salton123.saltonframeworkdemo

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import com.salton123.base.BaseSupportActivity
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.saltonframeworkdemo.mvp.MvpTestFragment

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/14 18:48
 * ModifyTime: 18:48
 * Description:
 */
class KotlinAty : BaseSupportActivity() {
    override fun getLayout(): Int {
        return R.layout.aty_kotlin
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        FragmentDelegate.newInstance(MvpTestFragment::class.java)
    }

    override fun initViewAndData() {
        var imageView = ImageView(context())
        imageView.setImageResource(R.mipmap.ic_launcher_round)
        (getRootView() as ViewGroup).addView(imageView)
        openActivity(MainActivity::class.java)

    }
}