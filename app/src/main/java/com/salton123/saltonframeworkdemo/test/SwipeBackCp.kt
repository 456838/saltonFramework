package com.salton123.saltonframeworkdemo.test

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.base.BaseSupportSwipeBackFragment
import com.salton123.saltonframeworkdemo.R

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/6 下午5:21
 * ModifyTime: 下午5:21
 * Description:
 */
class SwipeBackCp : BaseSupportSwipeBackFragment() {
    override fun getLayout(): Int = R.layout.cp_java

    override fun initVariable(savedInstanceState: Bundle?) {
        setSwipeBackEnable(true)
    }

    override fun initViewAndData() {
//        setParallaxOffset(0.5f)
    }

}