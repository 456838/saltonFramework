package com.salton123.ui.business;

import android.view.View;

import com.salton123.ui.base.BaseFragment;

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/20 11:38
 * ModifyTime: 11:38
 * Description:
 */
public abstract class TitleFragment<TITLE extends View> extends BaseFragment {

    @Override
    public TITLE getTitleBar() {
        return null;
    }
}
