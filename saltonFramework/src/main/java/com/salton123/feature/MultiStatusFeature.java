package com.salton123.feature;

import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.HintCallback;
import com.kingja.loadsir.callback.ProgressCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.salton123.saltonframework.R;
import com.salton123.ui.base.IComponentLife;
import com.salton123.util.FP;

import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2019/12/11 17:18
 * ModifyTime: 17:18
 * Description:
 */
public class MultiStatusFeature implements IFeature, Callback.OnReloadListener {
    public IComponentLife mIComponentLife;
    //状态页管理
    public LoadService mBaseLoadService;

    public MultiStatusFeature(IComponentLife componentLife) {
        this.mIComponentLife = componentLife;
    }

    @Override
    public void onBind() {
        LoadSir.Builder builder = new LoadSir.Builder()
                .addCallback(getInitStatus())
                .addCallback(getEmptyStatus())
                .addCallback(getErrorStatus())
                .addCallback(getLoadingStatus())
                .setDefaultCallback(getInitStatus().getClass());
        if (!FP.empty(getExtraStatus())) {
            for (Callback callback : getExtraStatus()) {
                builder.addCallback(callback);
            }
        }
        mBaseLoadService = builder.build().register(mIComponentLife.getRootView()
                .findViewById(R.id.salton_id_content_layout), this);
        mBaseLoadService.showCallback(getInitStatus().getClass());
    }

    Callback getInitStatus() {
        ProgressCallback loadingCallback = new ProgressCallback.Builder()
                .setTitle("Loading", R.style.salton_hint_title)
                .build();
        return loadingCallback;
    }

    Callback getLoadingStatus() {
        return new BlankStatus();
    }

    Callback getErrorStatus() {
        return new BlankStatus();
    }

    Callback getEmptyStatus() {
        HintCallback hintCallback = new HintCallback.Builder()
                .setTitle("Error", R.style.salton_hint_title)
                .setSubTitle("Sorry, buddy, I will try it again.")
                .setHintImg(R.drawable.salton_load_pic_failed)
                .build();
        return hintCallback;
    }

    @Override
    public void onUnBind() {

    }

    List<Callback> getExtraStatus() {
        return null;
    }

    @Override
    public void onReload(View v) {
        mBaseLoadService.showSuccess();
    }
}
