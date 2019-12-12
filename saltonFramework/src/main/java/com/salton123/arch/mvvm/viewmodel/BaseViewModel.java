package com.salton123.arch.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.salton123.arch.model.SingleLiveEvent;
import com.salton123.arch.model.BaseModel;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/12 15:22
 * Time: 15:22
 * Description:
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel,Consumer<Disposable> {
    protected M mModel;
    protected UIChangeLiveData mUIChangeLiveData;

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        this.mModel = model;
    }
    public UIChangeLiveData getUC() {
        if (mUIChangeLiveData == null) {
            mUIChangeLiveData = new UIChangeLiveData();
        }
        return mUIChangeLiveData;
    }

    public final class UIChangeLiveData extends SingleLiveEvent {
        private SingleLiveEvent<Boolean> showInitLoadViewEvent;
        private SingleLiveEvent<Boolean> showTransLoadingViewEvent;
        private SingleLiveEvent<Boolean> showNoDataViewEvent;
        private SingleLiveEvent<Boolean> showNetWorkErrViewEvent;
        private SingleLiveEvent<Map<String, Object>> startActivityEvent;
        private SingleLiveEvent<Void> finishActivityEvent;
        private SingleLiveEvent<Void> onBackPressedEvent;

        public SingleLiveEvent<Boolean> getShowInitLoadViewEvent() {
            return showInitLoadViewEvent = createLiveData(showInitLoadViewEvent);
        }

        public SingleLiveEvent<Boolean> getShowTransLoadingViewEvent() {
            return showTransLoadingViewEvent = createLiveData(showTransLoadingViewEvent);
        }

        public SingleLiveEvent<Boolean> getShowNoDataViewEvent() {
            return showNoDataViewEvent = createLiveData(showNoDataViewEvent);
        }

        public SingleLiveEvent<Boolean> getShowNetWorkErrViewEvent() {
            return showNetWorkErrViewEvent = createLiveData(showNetWorkErrViewEvent);
        }

        public SingleLiveEvent<Map<String, Object>> getStartActivityEvent() {
            return startActivityEvent = createLiveData(startActivityEvent);
        }

        public SingleLiveEvent<Void> getFinishActivityEvent() {
            return finishActivityEvent = createLiveData(finishActivityEvent);
        }

        public SingleLiveEvent<Void> getOnBackPressedEvent() {
            return onBackPressedEvent = createLiveData(onBackPressedEvent);
        }
    }
    protected SingleLiveEvent createLiveData(SingleLiveEvent liveData) {
        if (liveData == null) {
            liveData = new SingleLiveEvent();
        }
        return liveData;
    }
    public static final class ParameterField {
        public static String CLASS = "CLASS";
        public static String CANONICAL_NAME = "CANONICAL_NAME";
        public static String BUNDLE = "BUNDLE";
    }

    public void postShowInitLoadViewEvent(boolean show) {
        if (mUIChangeLiveData != null) {
            mUIChangeLiveData.showInitLoadViewEvent.postValue(show);
        }
    }

    public void postShowNoDataViewEvent(boolean show) {
        if (mUIChangeLiveData != null) {
            mUIChangeLiveData.showNoDataViewEvent.postValue(show);
        }
    }

    public void postShowTransLoadingViewEvent(boolean show) {
        if (mUIChangeLiveData != null) {
            mUIChangeLiveData.showTransLoadingViewEvent.postValue(show);
        }
    }

    public void postShowNetWorkErrViewEvent(boolean show) {
        if (mUIChangeLiveData != null) {
            mUIChangeLiveData.showNetWorkErrViewEvent.postValue(show);
        }
    }
    public void postStartActivityEvent(Class<?> clz, Bundle bundle) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CLASS, clz);
        if (bundle != null) {
            params.put(ParameterField.BUNDLE, bundle);
        }
        mUIChangeLiveData.startActivityEvent.postValue(params);
    }


    public void postFinishActivityEvent() {
        mUIChangeLiveData.finishActivityEvent.call();
    }


    public void postOnBackPressedEvent() {
        mUIChangeLiveData.onBackPressedEvent.call();
    }

    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void accept(Disposable disposable) throws Exception {
        if(mModel != null){
            mModel.addSubscribe(disposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mModel != null) {
            mModel.onCleared();
        }
    }
}
