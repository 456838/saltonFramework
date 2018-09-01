package com.salton123.base

import android.os.Bundle
import android.support.annotation.FloatRange
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.SwipeBackLayout
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackFragment
import me.yokeyword.fragmentation_swipeback.core.SwipeBackFragmentDelegate

/**
 * User: 巫金生(newSalton@163.com)
 * Date: 2017/6/6 23:45
 * Description:
 * Updated:
 */
abstract class BaseSupportFragment : SupportFragment(), IComponentLife, ISwipeBackFragment {
    internal val mFragmentDelegate by lazy { FragmentDelegate(this) }
    internal val mDelegate by lazy { SwipeBackFragmentDelegate(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate.onCreate(savedInstanceState)
        setSwipeBackEnable(false)
        super.onCreate(savedInstanceState)
        mFragmentDelegate.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mFragmentDelegate.onCreateView()?.let { attachToSwipeBack(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDelegate.onViewCreated(view, savedInstanceState)
        mFragmentDelegate.onViewCreated()
    }


    override fun attachToSwipeBack(view: View): View {
        return mDelegate.attachToSwipeBack(view)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mDelegate.onHiddenChanged(hidden)
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return mDelegate.swipeBackLayout
    }

    /**
     * 是否可滑动
     *
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
     * Set the offset of the parallax slip.
     */
    override fun setParallaxOffset(@FloatRange(from = 0.0, to = 1.0) offset: Float) {
        mDelegate.setParallaxOffset(offset)
    }

    override fun onDestroyView() {
        mDelegate.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        mFragmentDelegate.onDestroy()
        super.onDestroy()
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
        return this.activity as AppCompatActivity
    }

    override fun onClick(v: View?) {

    }

    override fun log(msg: String) {
        mFragmentDelegate.log(msg)
    }


    override fun longToast(toast: String) {
        mFragmentDelegate.longToast(toast)
    }

    override fun shortToast(toast: String) {
        mFragmentDelegate.shortToast(toast)
    }

    override fun <VT : View> f(id: Int): VT {
        return mFragmentDelegate.f(id)
    }

    override fun getRootView(): View {
        return mFragmentDelegate.getRootView()
    }

    override fun inflater(): LayoutInflater {
        return mFragmentDelegate.inflater()
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mFragmentDelegate.openActivity(clz, bundle)
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mFragmentDelegate.openActivityForResult(clz, bundle, requestCode)
    }

}
