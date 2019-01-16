package com.salton123.base.feature

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import java.util.ArrayList

/**
 * User: newSalton@outlook.com
 * Date: 2018/12/28 6:25 PM
 * ModifyTime: 6:25 PM
 * Description:
 */
abstract class BaseFeatureFragment : BaseSupportFragment() {
    private val mFeatures = ArrayList<IFeature>()

    fun addFeature(feature: IFeature) {
        this.mFeatures.add(feature)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (item in mFeatures) {
            item.onBind()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        for (item in mFeatures) {
            item.onUnBind()
        }
    }
}