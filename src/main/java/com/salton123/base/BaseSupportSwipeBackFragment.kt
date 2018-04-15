package com.salton123.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.salton123.manager.lifecycle.IFragmentLifeCycle
import com.salton123.util.LogUtils
import com.salton123.util.ViewUtils
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment
import java.io.Serializable

/**
 * User: 巫金生(newSalton@163.com)
 * Date: 2017/6/11 10:50
 * Description: 必须要在onViewCreated中return attachToSwipeBack(mContentView);
 * Updated:
 */
abstract class BaseSupportSwipeBackFragment : SwipeBackFragment(), IComponentLife {
    override fun onCreate(savedInstanceState: Bundle?) {
        initVariable(savedInstanceState)
        super.onCreate(savedInstanceState)
        setParallaxOffset(0.5f)
        IFragmentLifeCycle.Factory.get().init(fragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getRootView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndData()
        initListener()
    }

    override fun onDestroy() {
        IFragmentLifeCycle.Factory.get().unInit()
        super.onDestroy()
    }

    override fun initListener() {

    }

    override fun context(): Context {
        return context!!
    }

    override fun log(msg: String) {
        LogUtils.d(msg)
    }


    override fun longToast(toast: String) {
        Toast.makeText(activity, toast, Toast.LENGTH_LONG).show()
    }

    override fun shortToast(toast: String) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show()
    }

    override fun <VT : View> f(id: Int): VT {
        return ViewUtils.f(getRootView(), id)
    }

    override fun getRootView(): View {
        return inflater().inflate(getLayout(), null)
    }

    override fun inflater(): LayoutInflater {
        return LayoutInflater.from(context)
    }

    override fun openActivity(clz: Class<*>, bundle: Bundle?) {
        startActivity(Intent(context, clz).apply { bundle?.let { this@apply.putExtras(it) } })
    }

    override fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        startActivityForResult(Intent(context, clz).apply { bundle?.let { this@apply.putExtras(it) } }, requestCode)
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

