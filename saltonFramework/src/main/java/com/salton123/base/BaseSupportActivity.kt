package com.salton123.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.salton123.util.LogUtils
import com.salton123.util.ViewUtils
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SwipeBackLayout
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackActivity
import me.yokeyword.fragmentation_swipeback.core.SwipeBackActivityDelegate

/**
 * Created by Administrator on 2017/6/6.
 */

abstract class BaseSupportActivity : SupportActivity(), IComponentLife, ISwipeBackActivity {
    private val mDelegate by lazy { SwipeBackActivityDelegate(this) }
    private lateinit var mContentView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate.onCreate(savedInstanceState)
        initVariable(savedInstanceState)
        super.onCreate(savedInstanceState)
        mContentView = inflater().inflate(getLayout(), null)
        setContentView(mContentView)
        initViewAndData()
        initListener()
        setSwipeBackEnable(false)
        fragmentAnimator = DefaultHorizontalAnimator()
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
        return this
    }

    override fun onClick(v: View?) {

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

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDelegate.onPostCreate(savedInstanceState)
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return mDelegate.swipeBackLayout
    }

    /**
     * 是否可滑动
     * @param enable
     */
    override fun setSwipeBackEnable(enable: Boolean) {
        mDelegate.setSwipeBackEnable(enable)
    }

    override fun setEdgeLevel(edgeLevel: SwipeBackLayout.EdgeLevel) {
        mDelegate.setEdgeLevel(edgeLevel)
    }

    override fun setEdgeLevel(widthPixel: Int) {
        mDelegate.setEdgeLevel(widthPixel)
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     *
     * @return true: Activity优先滑动退出;  false: Fragment优先滑动退出
     */
    override fun swipeBackPriority(): Boolean {
        return mDelegate.swipeBackPriority()
    }
}
