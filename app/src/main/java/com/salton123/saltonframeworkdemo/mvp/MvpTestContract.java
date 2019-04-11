package com.salton123.saltonframeworkdemo.mvp;

import com.salton123.base.mvp.IBasePresenter;
import com.salton123.base.mvp.IBaseView;

/**
 * User: newSalton@outlook.com
 * Date: 2017/9/8 21:33
 * ModifyTime: 21:33
 * Description:
 */
public interface MvpTestContract {

    interface IView extends IBaseView {
        void onHello();
    }

    interface Presenter extends IBasePresenter<IView> {
        void sayHello();
    }
}
