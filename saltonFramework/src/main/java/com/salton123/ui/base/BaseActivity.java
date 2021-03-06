package com.salton123.ui.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

import com.salton123.feature.IFeature;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * User: newSalton@outlook.com
 * Date: 2019/2/16 19:01
 * ModifyTime: 19:01
 * Description:
 */
public abstract class BaseActivity extends SupportActivity implements IComponentLife {
    private ActivityDelegate mActivityDelegate = new ActivityDelegate(this);
    private List<IFeature> mFeatures = new ArrayList<>();
    public void addFeature(IFeature feature) {
        this.mFeatures.add(feature);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivityDelegate.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        for (IFeature item : mFeatures) {
            item.onBind();
        }
        setContentView(mActivityDelegate.onCreateView());
        mActivityDelegate.onViewCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (IFeature item : mFeatures) {
            item.onUnBind();
        }
    }

    @Override
    public View getRootView() {
        return mActivityDelegate.getRootView();
    }

    @Override
    public <B extends View> B getTitleBar() {
        return null;
    }

    @Override
    public Activity activity() {
        return mActivityDelegate.activity();
    }

    @Override
    public LayoutInflater inflater() {
        return mActivityDelegate.inflater();
    }

    @Override
    public <T extends View> T f(int resId) {
        return mActivityDelegate.f(resId);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void longToast(String toast) {
        mActivityDelegate.longToast(toast);
    }

    @Override
    public void shortToast(String toast) {
        mActivityDelegate.shortToast(toast);
    }

    @Override
    public void log(String msg) {
        mActivityDelegate.log(msg);
    }

    @Override
    public void openActivity(Class<?> clz, Bundle bundle) {
        mActivityDelegate.openActivity(clz, bundle);
    }


    public void openActivity(Class<?> clz) {
        mActivityDelegate.openActivity(clz, new Bundle());
    }

    public void openActivityForResult(Class<?> clz, int requestCode) {
        mActivityDelegate.openActivityForResult(clz, new Bundle(), requestCode);
    }
    @Override
    public void openActivityForResult(Class<?> clz, Bundle bundle, int requestCode) {
        mActivityDelegate.openActivityForResult(clz, bundle, requestCode);
    }

    @Override
    public void setListener(int... ids) {
        mActivityDelegate.setListener(ids);
    }

    @Override
    public void setListener(View... views) {
        mActivityDelegate.setListener(views);
    }

    @Override
    public void show(View... views) {
        mActivityDelegate.show(views);
    }

    @Override
    public void hide(View... views) {
        mActivityDelegate.hide(views);
    }

    @Override
    public void onClick(View v) {

    }

}
