package com.salton123.base

import android.app.Activity
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
        (mFragment as Activity).startActivity(Intent(mComponentLife.context(), clz).let { it.putExtras(bundle) })
    }

    fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        (mFragment as Activity).startActivityForResult(Intent(mComponentLife.context(), clz).let { it.putExtras(bundle) }, requestCode)
    }

    companion object {
        open val ARG_ITEM = "arg_item"

        open fun <T : Fragment> newInstance(clz: Class<T>): T? {
            val bundle = Bundle()
            var fragment: T? = null
            try {
                fragment = clz.newInstance()
                fragment!!.arguments = bundle
            } catch (e: Throwable) {

            }

            return fragment
        }

        open fun <T : Fragment> newInstance(clz: Class<T>, bundle: Bundle): T {
            clz.newInstance().arguments
            return clz.newInstance().also { it.arguments = bundle }
        }

        open fun <T : Fragment> newInstance(clz: Class<T>, value: Serializable): T {
            return clz.newInstance().also {
                it.arguments = Bundle().also {
                    it.putSerializable(ARG_ITEM, value)
                }
            }
        }

        open fun <T : Fragment> newInstance(clz: Class<T>, value: Parcelable): T {
            return clz.newInstance().also {
                it.arguments = Bundle().also {
                    it.putParcelable(ARG_ITEM, value)
                }
            }
        }
    }


}