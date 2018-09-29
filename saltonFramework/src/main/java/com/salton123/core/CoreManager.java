package com.salton123.core;

import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/29 上午9:51
 * ModifyTime: 上午9:51
 * Description:
 */
public class CoreManager {
    private final String TAG = "CoreManager";
    private static SimpleArrayMap<Class<? extends IBaseCore>, IBaseCore> sCoreMapper = new SimpleArrayMap();

    public static <T extends IBaseCore> void register(Class<T> coreInterface, IBaseCore coreImpl) {
        if (!sCoreMapper.containsKey(coreInterface)) {
            sCoreMapper.put(coreInterface, coreImpl);
        } else {
            Log.i("CoreManager", "[register] coreInterface=" + coreInterface.getSimpleName() + "has registered");
        }
    }

    public static <T extends IBaseCore> T getCore(Class<T> cls) {
        return (T) sCoreMapper.get(cls);
    }
}
