package com.salton123.feature;

import com.salton123.feature.IFeature;
import com.salton123.util.EventUtil;

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/16 10:11
 * ModifyTime: 10:11
 * Description:
 */
public class EventBusFeature implements IFeature {
    private Object target;

    public EventBusFeature(Object target) {
        this.target = target;
    }

    @Override
    public void onBind() {
        EventUtil.register(target);
    }

    @Override
    public void onUnBind() {
        EventUtil.unregister(target);
    }
}
