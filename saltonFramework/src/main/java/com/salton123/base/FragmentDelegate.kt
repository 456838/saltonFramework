package com.salton123.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.salton123.manager.lifecycle.IFragmentLifeCycle
import com.salton123.util.LogUtils
import com.salton123.util.ViewUtils
import java.io.Serializable

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/19 17:14
 * ModifyTime: 17:14
 * Description:
 */
class FragmentDelegate(componentLife: IComponentLife) {
    private var mComponentLife: IComponentLife = componentLife
    private var mFragment: Fragment

    init {
        if (mComponentLife !is Fragment) {
            throw RuntimeException("Must extends Fragment")
        }
        mFragment = componentLife as Fragment
    }

    fun onCreate(savedInstanceState: Bundle?) {
        mComponentLife.initVariable(savedInstanceState)
        IFragmentLifeCycle.Factory.get().init(mFragment.fragmentManager)
    }

    fun onCreateView(): View? {
        return getRootView()
    }

    fun onViewCreated() {
        mComponentLife.initViewAndData()
        mComponentLife.initListener()
    }

    fun onDestroy() {
        IFragmentLifeCycle.Factory.get().unInit()
    }

    fun log(msg: String) {
        LogUtils.d(msg)
    }

    fun longToast(toast: String) {
        Toast.makeText(mComponentLife.context(), toast, Toast.LENGTH_LONG).show()
    }

    fun shortToast(toast: String) {
        Toast.makeText(mComponentLife.context(), toast, Toast.LENGTH_SHORT).show()
    }

    fun <VT : View> f(id: Int): VT {
        return ViewUtils.f(getRootView(), id)
    }

    fun getRootView(): View {
        return inflater().inflate(mComponentLife.getLayout(), null)
    }

    fun inflater(): LayoutInflater {
        return LayoutInflater.from(mComponentLife.context())
    }

    fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mFragment.activity.startActivity(Intent(mComponentLife.context(), clz).let { it.putExtras(bundle) })
    }

    fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mFragment.activity.startActivityForResult(Intent(mComponentLife.context(), clz).let { it.putExtras(bundle) }, requestCode)
    }

    companion object {
        val ARG_ITEM = "arg_item"

        fun <T : Fragment> newInstance(
            clz: Class<T>
            , bundle: Bundle? = null
            , serial: Serializable? = null
            , parcel: Parcelable? = null
        ): T {
            return clz.newInstance().apply {
                if (bundle != null) {
                    bundle.let { this.arguments = bundle }
                    serial?.let { this.arguments = bundle.apply { this@apply.putSerializable(ARG_ITEM, it) } }
                    parcel?.let { this.arguments = bundle.apply { this@apply.putParcelable(ARG_ITEM, it) } }
                } else {
                    serial?.let { this.arguments = Bundle.EMPTY.apply { this@apply.putSerializable(ARG_ITEM, it) } }
                    parcel?.let { this.arguments = Bundle.EMPTY.apply { this@apply.putParcelable(ARG_ITEM, it) } }
                }
            }
        }
    }


}