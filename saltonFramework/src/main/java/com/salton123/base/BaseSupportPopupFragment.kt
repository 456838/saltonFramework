package com.salton123.base

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.salton123.event.PopupStyle


/**
 * User: newSalton@outlook.com
 * Date: 2018/5/5 下午12:00
 * ModifyTime: 下午12:00
 * Description:
 * set your style onCreate setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog)
 *
 *
 *
 */
abstract class BaseSupportPopupFragment : DialogFragment(), IComponentLife {
    private lateinit var mDelegate: FragmentDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        mDelegate = FragmentDelegate(this)
        mDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setStyle(popupStyle().style, popupStyle().theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mDelegate.onCreateView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDelegate.onViewCreated()
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        super.onDestroy()
    }


    fun popupStyle(): PopupStyle {
        return PopupStyle()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(popupStyle().canceledOnTouchOutside)
        dialog.window.setWindowAnimations(popupStyle().anim)
        dialog.window.setBackgroundDrawableResource(popupStyle().backgroundDrawableResource)
        dialog.window.setLayout(popupStyle().layoutWidth, popupStyle().layoutHeight)
        dialog.window.setGravity(popupStyle().gravity)
        return dialog
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

    override fun initListener() {

    }

    override fun log(msg: String) {
        mDelegate.log(msg)
    }


    override fun longToast(toast: String) {
        mDelegate.longToast(toast)
    }

    override fun shortToast(toast: String) {
        mDelegate.shortToast(toast)
    }

    override fun <VT : View> f(id: Int): VT {
        return mDelegate.f(id)
    }

    override fun getRootView(): View {
        return mDelegate.getRootView()
    }

    override fun inflater(): LayoutInflater {
        return mDelegate.inflater()
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mDelegate.openActivity(clz, bundle)
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mDelegate.openActivityForResult(clz, bundle, requestCode)
    }

}