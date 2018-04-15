package com.salton123.manager.lifecycle;

import android.support.v4.app.FragmentManager;

import com.salton123.core.IBaseCore;

/**
 * User: newSalton@outlook.com
 * Date: 2018/3/7 19:08
 * ModifyTime: 19:08
 * Description:
 */
public interface IFragmentLifeCycle extends IBaseCore {
    void init(FragmentManager fragmentManager);

    void unInit();

    class Factory {
        public static IFragmentLifeCycle get() {
            return FragmentLifeCycleManager.INSTANCE();
        }
    }
}
