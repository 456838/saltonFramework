package com.salton123.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.salton123.base.feature.IFeature
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SwipeBackLayout
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackActivity
import me.yokeyword.fragmentation_swipeback.core.SwipeBackActivityDelegate
import java.util.ArrayList

/**
 * Created by Administrator on 2017/6/6.
 */

abstract class BaseSupportSwipeBackActivity : SupportActivity(), IComponentLife, ISwipeBackActivity {
    private val mDelegate by lazy { SwipeBackActivityDelegate(this) }
    private val mActivityDelegate by lazy { ActivityDelegate(this) }
    private val mFeatures = ArrayList<IFeature>()

    fun addFeature(feature: IFeature) {
        this.mFeatures.add(feature)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate.onCreate(savedInstanceState)
        mActivityDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(mActivityDelegate.onCreateView())
        mActivityDelegate.onViewCreated()
        setSwipeBackEnable(false)
        fragmentAnimator = DefaultHorizontalAnimator()
        for (item in mFeatures) {
            item.onBind()
        }
    }

    override fun initListener() {

    }

    override fun setListener(vararg ids: Int) {
        mActivityDelegate.setListener(*ids)
    }

    override fun setListener(vararg views: View) {
        mActivityDelegate.setListener(*views)
    }

    override fun activity(): AppCompatActivity {
        return mActivityDelegate.activity()
    }

    override fun onClick(v: View?) {

    }

    override fun log(msg: String) {
        mActivityDelegate.log(msg)
    }

    override fun <VT : View> f(id: Int): VT {
        return mActivityDelegate.f(id)
    }

    override fun getRootView(): View {
        return mActivityDelegate.getRootView()
    }

    override fun longToast(toast: String) {
        mActivityDelegate.longToast(toast)
    }

    override fun shortToast(toast: String) {
        mActivityDelegate.shortToast(toast)
    }


    override fun inflater(): LayoutInflater {
        return mActivityDelegate.inflater()
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mActivityDelegate.openActivity(clz, bundle)
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mActivityDelegate.openActivityForResult(clz, bundle, requestCode)
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

    override fun onDestroy() {
        super.onDestroy()
        mActivityDelegate.onDestroy()
        for (item in mFeatures) {
            item.onUnBind()
        }
    }

    override fun getTitleBar(): View? {
        return null
    }
}
