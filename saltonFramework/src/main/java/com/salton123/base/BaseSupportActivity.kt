package com.salton123.base

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.salton123.base.feature.IFeature
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import java.util.ArrayList

/**
 * Created by Administrator on 2017/6/6.
 */

abstract class BaseSupportActivity : SupportActivity(), IComponentLife {
    private val mActivityDelegate by lazy { ActivityDelegate(this) }
    private val mFeatures = ArrayList<IFeature>()

    fun addFeature(feature: IFeature) {
        this.mFeatures.add(feature)
    }
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        mActivityDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        for (item in mFeatures) {
            item.onBind()
        }
        setContentView(mActivityDelegate.onCreateView())
        mActivityDelegate.onViewCreated()
        fragmentAnimator = DefaultHorizontalAnimator()
    }

    override fun initListener() {

    }

    override fun getTitleBar(): View? {
        return null
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

    override fun onDestroy() {
        super.onDestroy()
        mActivityDelegate.onDestroy()
        for (item in mFeatures) {
            item.onUnBind()
        }
    }
}
