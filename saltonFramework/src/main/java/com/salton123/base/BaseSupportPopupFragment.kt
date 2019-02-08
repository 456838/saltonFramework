package com.salton123.base

import android.app.Dialog
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.salton123.base.feature.IFeature
import com.salton123.event.PopupStyle
import com.salton123.util.PopupStyleHelper
import java.util.ArrayList


/**
 * User: newSalton@outlook.com
 * Date: 2018/5/5 下午12:00
 * ModifyTime: 下午12:00
 * Description:
 * set your style onCreate setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog)
 *
 */
abstract class BaseSupportPopupFragment : DialogFragment(), IComponentLife {
    private val mFragmentDelegate by lazy { FragmentDelegate(this) }
    private val mFeatures = ArrayList<IFeature>()

    fun addFeature(feature: IFeature) {
        this.mFeatures.add(feature)
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFragmentDelegate.onCreate(savedInstanceState)
        setStyle(popupStyle().style, popupStyle().theme)
        for (item in mFeatures) {
            item.onBind()
        }
    }

    open fun popupStyle(): PopupStyle {
        return PopupStyle()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = PopupStyleHelper.fullScreenStyle(super.onCreateDialog(savedInstanceState))
        dialog.setCanceledOnTouchOutside(popupStyle().canceledOnTouchOutside)
        dialog.window.setWindowAnimations(popupStyle().anim)
        dialog.window.setBackgroundDrawableResource(popupStyle().backgroundDrawableResource)
        dialog.window.setLayout(popupStyle().layoutWidth, popupStyle().layoutHeight)
        dialog.window.setGravity(popupStyle().gravity)
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mFragmentDelegate.onCreateView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFragmentDelegate.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        mFragmentDelegate.onDestroy()
        for (item in mFeatures) {
            item.onUnBind()
        }
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

    override fun getTitleBar(): View? {
        return null
    }

}