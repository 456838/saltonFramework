package com.salton123.base

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
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
    private lateinit var rootView: View

    init {
        if (mComponentLife !is Fragment) {
            throw RuntimeException("Must extends Fragment")
        }
        mFragment = componentLife as Fragment
    }

    fun onCreate(savedInstanceState: Bundle?) {
        mComponentLife.initVariable(savedInstanceState)
//        IFragmentLifeCycle.Factory.get().init(mFragment.fragmentManager)
    }

    fun onCreateView(): View? {
        rootView = inflater().inflate(mComponentLife.getLayout(), null)
        return rootView
    }

    fun onViewCreated() {
        mComponentLife.initViewAndData()
        mComponentLife.initListener()
    }

    fun onDestroy() {
//        IFragmentLifeCycle.Factory.get().unInit()
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
        return ViewUtils.f(rootView, id)
    }

    fun getRootView(): View {
        return rootView
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
        /**
         * 兼容java调kt
         */
        fun <T : Fragment> newInstance(clz: Class<T>): T {
            return clz.newInstance()
        }

        fun <T : Fragment> newInstance(clz: Class<T>, bundle: Bundle): T {
            clz.newInstance().arguments
            return clz.newInstance().also { it.arguments = bundle }
        }

        fun <T : Fragment> newInstance(clz: Class<T>, value: Serializable): T {
            return clz.newInstance().also {
                it.arguments = Bundle().also {
                    it.putSerializable(ARG_ITEM, value)
                }
            }
        }

        fun <T : Fragment> newInstance(clz: Class<T>, value: Parcelable): T {
            return clz.newInstance().also {
                it.arguments = Bundle().also {
                    it.putParcelable(ARG_ITEM, value)
                }
            }
        }

        /**
         * 兼容java调kt end
         */
        fun <T : Fragment> newInstance(
            clz: Class<T>
            ,  bundle: Bundle? = null
            , serial: Serializable? = null
            , parcel: Parcelable? = null
        ): T {
            return clz.newInstance().apply {
                if (bundle != null) {
                    bundle.let { this.arguments = bundle }
                    serial?.let { this.arguments = bundle.apply { putSerializable(ARG_ITEM, it) } }
                    parcel?.let { this.arguments = bundle.apply { putParcelable(ARG_ITEM, it) } }
                } else {
                    serial?.let { this.arguments = Bundle.EMPTY.apply { putSerializable(ARG_ITEM, it) } }
                    parcel?.let { this.arguments = Bundle.EMPTY.apply { putParcelable(ARG_ITEM, it) } }
                }
            }
        }
    }


}