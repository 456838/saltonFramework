package com.salton123.saltonframeworkdemo.mvp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.salton123.base.mvp.ui.BaseSupportPresenterActivity;
import com.salton123.saltonframeworkdemo.KotlinAty;
import com.salton123.saltonframeworkdemo.MainActivity;
import com.salton123.saltonframeworkdemo.R;

/**
 * User: newSalton@outlook.com
 * Date: 2017/9/8 21:32
 * ModifyTime: 21:32
 * Description:
 */
public class MvpTestAty extends BaseSupportPresenterActivity<MvpTestContract.Presenter> implements MvpTestContract.IView {
    @Override
    public void onHello() {
        longToast("onHello");
        openActivity(MainActivity.class, null);
    }

    TextView tv_hello;
    FrameLayout fl_test;

    @Override
    public int getLayout() {
        return R.layout.aty_mvp_test;
    }

    @Override
    public void initVariable(Bundle savedInstanceState) {
        mPresenter = new MvpTestPresenter();
    }

    @Override
    public void initViewAndData() {
        tv_hello = f(R.id.tv_hello);
        fl_test = f(R.id.fl_test);
        FragmentManager fragmentManager = getSupportFragmentManager();
        // loadRootFragment(R.id.fl_test, MvpTestFragment.Companion.newInstance(MvpTestFragment.class));
        // loadRootFragment(R.id.fl_test, FragmentDelegate.Companion.newInstance(RecyclerTestComponent.class));
    }

    @Override
    public void initListener() {
        tv_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mPresenter.sayHello();
                // startActivity(Intent(KotlinAty::java.class));
                openActivity(KotlinAty.class, null);
            }
        });
    }
}
