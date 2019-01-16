package com.salton123.base.feature

import com.salton123.base.bus.EventFactory


/**
 * User: newSalton@outlook.com
 * Date: 2018/12/28 6:21 PM
 * ModifyTime: 6:21 PM
 * Description:
 */

class EventBusFeature(var obj: Any) : IFeature {

    override fun onBind() {
        EventFactory.register(obj)
    }

    override fun onUnBind() {
        EventFactory.unregister(obj)
    }

}