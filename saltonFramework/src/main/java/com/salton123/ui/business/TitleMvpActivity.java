package com.salton123.ui.business;

import android.view.View;

import com.salton123.ui.mvp.BaseMvpActivity;
import com.salton123.ui.mvp.BasePresenter;

/**
 * User: newSalton@outlook.com
 * Date: 2019/5/20 11:38
 * ModifyTime: 11:38
 * Description:
 */
public abstract class TitleMvpActivity<
        TITLE extends View,
        PRESENTER extends BasePresenter>
        extends BaseMvpActivity<PRESENTER> {

    @Override
    public TITLE getTitleBar() {
        return null;
    }
}
