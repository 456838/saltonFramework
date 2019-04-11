package com.salton123.saltonframeworkdemo.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.salton123.base.mvp.ui.BaseMvpFragment;
import com.salton123.saltonframeworkdemo.R;
import com.salton123.util.RxUtils;

import io.reactivex.functions.Consumer;

/**
 * User: newSalton@outlook.com
 * Date: 2017/9/8 21:32
 * ModifyTime: 21:32
 * Description:
 */
public class MvpTestFragment extends BaseMvpFragment<MvpTestContract.Presenter> implements MvpTestContract.IView {
    @Override
    public void onHello() {

//       Observable<String> observable =
//        RxUtils.createData(Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                e.onNext("onHello By Rxjava");
//            }
//        }));
//        .compose(RxUtils.rxSchedulerHelper()).subscribe();
        RxUtils.createData("hello").compose(RxUtils.<String>rxSchedulerHelper()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                longToast(s + "123456");
            }
        });
        pop();
//               .subscribe(new Consumer<Observable<String>>() {
//            @Override
//            public void accept(Observable<String> stringObservable) throws Exception {
//                toast("onHello2222");
//            }
//        });


    }

    TextView tv_hello;

    @Override
    public int getLayout() {
        return R.layout.fm_mvp_test;
    }

    @Override
    public void initVariable(Bundle savedInstanceState) {
        mPresenter = new MvpTestPresenter();
    }

    @Override
    public void initViewAndData() {
        tv_hello = f(R.id.tv_hello);
    }

    @Override
    public void initListener() {
        tv_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.sayHello();
            }
        });
    }


}
