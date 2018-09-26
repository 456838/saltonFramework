package com.salton123.app;

import android.util.Log;

import com.salton123.util.RxUtils;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/24 下午12:31
 * ModifyTime: 下午12:31
 * Description:
 */
public class Test {
    private void test(IFutureTaskPriority priority) {
        Observable.zip(lowPriority(), mediumPriority(), highPrioirty(),
                new Function3<Boolean, Boolean, Boolean, Boolean>() {
                    @Override
                    public Boolean apply(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3)
                            throws Exception {
                        return aBoolean & aBoolean2 & aBoolean3;
                    }
                });
        Observable.merge(lowPriority(),mediumPriority(),highPrioirty())
                .compose(RxUtils.<Boolean>rxSchedulerHelper())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.e("","");
                    }
                });
    }

    private Observable<Boolean> lowPriority() {
        return Observable.just(true);
    }

    private Observable<Boolean> mediumPriority() {
        return Observable.just(true);
    }

    private Observable<Boolean> highPrioirty() {
        return Observable.just(true);
    }
}
