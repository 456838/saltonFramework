package com.salton123.saltonframeworkdemo.mvp;

import com.salton123.base.mvp.presenter.RxPresenter;

/**
 * User: newSalton@outlook.com
 * Date: 2017/9/8 21:35
 * ModifyTime: 21:35
 * Description:
 */
public class MvpTestPresenter extends RxPresenter<MvpTestContract.IView> implements MvpTestContract.Presenter {
    @Override
    public void sayHello() {
        mView.onHello();
    }
}
