package com.salton123.arch.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.salton123.arch.mvvm.viewmodel.BaseViewModel;
import com.salton123.ui.base.BaseActivity;

import java.util.Map;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/12 15:22
 * Time: 15:22
 * Description:
 */
public abstract class BaseMvvmActivity<VM extends BaseViewModel> extends BaseActivity {
    protected VM mViewModel;

    @Override
    public void initListener() {
        super.initListener();
        initParam();
    }

    public void initParam() {
        initViewModel();
        initBaseViewObservable();
        initViewObservable();
    }

    private void initViewModel() {
        mViewModel = createViewModel();
        getLifecycle().addObserver(mViewModel);
    }

    public VM createViewModel() {
        return ViewModelProviders.of(this, onBindViewModelFactory()).get(onBindViewModel());
    }

    public abstract Class<VM> onBindViewModel();

    public abstract Factory onBindViewModelFactory();

    public abstract void initViewObservable();

    protected void initBaseViewObservable() {
        mViewModel.getUC().getShowInitLoadViewEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean show) {
                showInitLoadView(show);
            }
        });
        mViewModel.getUC().getShowTransLoadingViewEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean show) {
                showTransLoadingView(show);
            }
        });
        mViewModel.getUC().getShowNoDataViewEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean show) {
                showNoDataView(show);
            }
        });
        mViewModel.getUC().getShowNetWorkErrViewEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean show) {
                showNetWorkErrView(show);
            }
        });
        mViewModel.getUC().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                openActivity(clz, bundle);
            }
        });
        mViewModel.getUC().getFinishActivityEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                finish();
            }
        });
        mViewModel.getUC().getOnBackPressedEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                onBackClick();
            }
        });
    }
}
