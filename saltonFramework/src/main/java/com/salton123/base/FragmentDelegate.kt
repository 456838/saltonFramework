package com.salton123.base

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import java.io.Serializable

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/19 17:14
 * ModifyTime: 17:14
 * Description:
 */
class FragmentDelegate(componentLife: IComponentLife) : LifeDelegate(componentLife) {

    override fun activity(): AppCompatActivity = mHost.activity as AppCompatActivity

    private var mHost: Fragment

    init {
        if (mComponentLife !is Fragment) {
            throw RuntimeException("instance must Fragment")
        }
        mHost = componentLife as Fragment
    }

    fun openActivity(clz: Class<*>) {
        activity().startActivity(Intent(mComponentLife.activity(), clz))
    }

    fun openActivity(clz: Class<*>, bundle: Bundle?) {
        mHost.startActivity(Intent(mComponentLife.activity(), clz).let { it.putExtras(bundle) })
    }

    fun openActivityForResult(clz: Class<*>, bundle: Bundle?, requestCode: Int) {
        mHost.startActivityForResult(Intent(mComponentLife.activity(), clz).let { it.putExtras(bundle) }, requestCode)
    }

    fun openActivityForResult(clz: Class<*>, requestCode: Int) {
        mHost.startActivityForResult(Intent(mComponentLife.activity(), clz), requestCode)
    }

    companion object {
        const val ARG_ITEM = "arg_item"

        @JvmStatic
        fun <T : Fragment> newInstance(clz: Class<T>): T {
            return clz.newInstance()
        }

        @JvmStatic
        fun <T : Fragment> newInstance(clz: Class<T>, value: Serializable): T {
            return clz.newInstance().also {
                it.arguments = Bundle().also {
                    it.putSerializable(ARG_ITEM, value)
                }
            }
        }

        @JvmStatic
        fun <T : Fragment> newInstance(clz: Class<T>, value: Parcelable): T {
            return clz.newInstance().also {
                it.arguments = Bundle().also {
                    it.putParcelable(ARG_ITEM, value)
                }
            }
        }

        @JvmStatic
        fun <T : Fragment> newInstance(clz: Class<T>, value: Bundle): T {
            return clz.newInstance().also {
                it.arguments = value
            }
        }

        fun showPopupComp(@NonNull fragment: Fragment, @NonNull fragmentManager: FragmentManager) {
            val ft = fragmentManager.beginTransaction()
            ft.add(fragment, fragment.javaClass.simpleName)
            ft.commitAllowingStateLoss()
        }

        fun <T : Fragment> showPopupComp(
            @NonNull clz: Class<T>,
            @NonNull fragmentManager: FragmentManager) {
            var fragment = newInstance(clz)
            showPopupComp(fragment, fragmentManager)
        }

    }

}