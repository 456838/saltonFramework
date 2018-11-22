package com.salton123.saltonframeworkdemo.test

import android.os.Bundle
import com.salton123.base.BaseSupportSwipeBackActivity
import com.salton123.base.FragmentDelegate
import com.salton123.saltonframeworkdemo.R

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/6 下午5:20
 * ModifyTime: 下午5:20
 * Description:
 */

class SwipeBackAty : BaseSupportSwipeBackActivity() {
    override fun getLayout(): Int = R.layout.salton_fm_container

    override fun initVariable(savedInstanceState: Bundle?) {
        setSwipeBackEnable(false)
    }

    override fun initViewAndData() {
        loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(RecyclerTestComponent::class.java))
    }

}