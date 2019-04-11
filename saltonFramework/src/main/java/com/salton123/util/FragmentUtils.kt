package com.salton123.util

import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.IdRes
import android.support.annotation.NonNull
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import java.io.Serializable

/**
 * User: newSalton@outlook.com
 * Date: 2019/3/26 13:50
 * ModifyTime: 13:50
 * Description:
 */

object FragmentUtils {
    const val ARG_ITEM = "arg_item"
    private val TAG = "FragmentUtils"
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

    @JvmStatic
    fun showPopupComp(@NonNull fragment: Fragment, @NonNull fragmentManager: FragmentManager) {
        val ft = fragmentManager.beginTransaction()
        ft.add(fragment, fragment.javaClass.simpleName)
        ft.commitAllowingStateLoss()
    }

    @JvmStatic
    fun <T : Fragment> showPopupComp(
            @NonNull clz: Class<T>,
            @NonNull fragmentManager: FragmentManager) {
        var fragment = newInstance(clz)
        showPopupComp(fragment, fragmentManager)
    }

    @JvmStatic
    fun add(manager: FragmentManager, framgment: Fragment, layoutId: Int) {
        val transaction = manager.beginTransaction()
        transaction.add(layoutId, framgment)
        transaction.commitAllowingStateLoss()
    }

    @JvmStatic
    fun showDialogFragment(
            manager: FragmentManager?,
            fragment: DialogFragment?,
            tag: String?) {
        if (manager == null || fragment == null || tag == null) {
            Log.i(TAG, "[showDialogFragment] params == null")
            return
        }
        try {
            manager.beginTransaction()
                    .add(fragment, tag)
                    .commitAllowingStateLoss()
            Log.i(TAG, "showDialogFragment")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "ex=" + e.localizedMessage)
        }

    }

    @JvmStatic
    fun <T : Fragment> addOrReplaceFragment(
            fragmentManager: FragmentManager?,
            @IdRes layoutId: Int, targetFragment: T) {
        if (fragmentManager == null) {
            Log.i(TAG, "fragmentManager == null")
            return
        }
        val fragment = fragmentManager.findFragmentById(layoutId)
        val transaction = fragmentManager.beginTransaction()
        if (fragment != null) {
            transaction.replace(layoutId, targetFragment)
            Log.i(TAG, "replace fragment")
        } else {
            Log.i(TAG, "add fragment")
            transaction.add(layoutId, targetFragment)
        }
        transaction.commitAllowingStateLoss()
    }

    @JvmStatic
    fun <T : Fragment> removeFragment(
            fragmentManager: FragmentManager?,
            @IdRes layoutId: Int) {
        if (fragmentManager == null) {
            Log.i(TAG, "fragmentManager == null")
            return
        }
        val fragment = fragmentManager.findFragmentById(layoutId)
        val transaction = fragmentManager.beginTransaction()
        if (fragment != null) {
            transaction.remove(fragment)
            Log.i(TAG, "remove fragment")
        } else {
            Log.i(TAG, "remove fragment fail")
        }
        transaction.commitAllowingStateLoss()
    }
}