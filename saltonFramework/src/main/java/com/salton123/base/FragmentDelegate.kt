package com.salton123.base

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
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

    companion object {
        const val ARG_ITEM = "arg_item"
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
        @JvmOverloads
        fun <T : Fragment> newInstance(
            clz: Class<T>
            , bundle: Bundle? = null
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