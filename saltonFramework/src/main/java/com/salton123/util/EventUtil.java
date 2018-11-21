package com.salton123.util;

import com.hwangjr.rxbus.RxBus;

public class EventUtil {

    public static void register(Object context) {
        RxBus.get().register(context);
    }

    public static void unregister(Object context) {
        RxBus.get().unregister(context);
    }

    public static void sendEvent(Object object) {
        RxBus.get().post(object);
    }
}
