package com.salton123.saltonframeworkdemo.mvp;

import com.salton123.mvp.presenter.BasePresenter;
import com.salton123.mvp.view.BaseView;

/**
 * User: newSalton@outlook.com
 * Date: 2017/9/8 21:33
 * ModifyTime: 21:33
 * Description:
 */
public interface MvpTestContract {

    interface IView extends BaseView{
        void onHello();
    }

    interface  Presenter extends BasePresenter<IView>{
        void sayHello();
    }
}
