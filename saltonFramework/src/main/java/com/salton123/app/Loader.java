package com.salton123.app;

import android.annotation.SuppressLint;
import android.util.Log;

import com.salton123.task.AbsTask;
import com.salton123.task.Callback;
import com.salton123.task.TaskControllerImpl;
import com.salton123.util.RxUtils;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/25 下午1:47
 * ModifyTime: 下午1:47
 * Description:
 */
public class Loader {

    @SuppressLint("CheckResult")
    public void init() {
        // Observable.zip(highPriority(), mediumPriority(), lowPriority(), new Function3<Boolean, Boolean, Boolean, Boolean>() {
        //     @Override
        //     public Boolean apply(Boolean aBoolean, Boolean aBoolean2, Boolean aBoolean3) throws Exception {
        //         Log.e("newsalton","aBoolean && aBoolean2 && aBoolean3");
        //         return aBoolean && aBoolean2 && aBoolean3;
        //     }
        // }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
        //     @Override
        //     public void accept(Boolean aBoolean) throws Exception {
        //         Log.e("newsalton", "init:" + Thread.currentThread());
        //     }
        // });
        // Observable.merge(lowPriority(), mediumPriority())
        //         .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        //         .subscribe(new Consumer<Boolean>() {
        //     @Override
        //     public void accept(Boolean aBoolean) throws Exception {
        //         Log.e("newsalton", "init" + Thread.currentThread());
        //     }
        // });
        // Observable.just(highPriority(), mediumPriority(), lowPriority())
        //         .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        //         .subscribe(new Consumer<Observable<Boolean>>() {
        //             @Override
        //             public void accept(Observable<Boolean> booleanObservable) throws Exception {
        //                 booleanObservable.subscribe();
        //                 Log.e("newsalton", "init" + Thread.currentThread());
        //             }
        //         });

        // final ArrayList<String> stringList = new ArrayList<>();
        // stringList.add("file://a");
        // stringList.add("file://b");
        // stringList.add("file://c");
        //
        // Observable.just("token").flatMap(new Function<String, ObservableSource<String>>() {
        //     @Override
        //     public ObservableSource<String> apply(final String token) throws Exception {
        //         return Observable.fromIterable(stringList).map(new Function<String, String>() {
        //             @Override
        //             public String apply(String s) throws Exception {
        //                 return token + s;
        //             }
        //         }).flatMap(new Function<String, ObservableSource<String>>() {
        //             @Override
        //             public ObservableSource<String> apply(String s) throws Exception {
        //                 return Observable.just(s).observeOn(Schedulers.io()).map(new Function<String, String>() {
        //                     @Override
        //                     public String apply(String s) throws Exception {
        //                         Log.d("newsalton", "testRxjavaToList request " + getCurrentName() + " " + s);
        //                         Thread.sleep(2000);
        //                         return "request done:" + s;
        //                     }
        //                 });
        //             }
        //         });
        //     }
        // }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
        //     @Override
        //     public void accept(String s) throws Exception {
        //         Log.d("newsalton", "testRxjavaToList subscribe " + getCurrentName() + " " + s);
        //     }
        // });

        // try {
        //     TaskControllerImpl.registerInstance().startTasks(new Callback.GroupCallback<AbsTask<Boolean>>() {
        //         @Override
        //         public void onSuccess(AbsTask<Boolean> item) {
        //
        //         }
        //
        //         @Override
        //         public void onError(AbsTask<Boolean> item, Throwable ex, boolean isOnCallback) {
        //
        //         }
        //
        //         @Override
        //         public void onCancelled(AbsTask<Boolean> item, CancelledException cex) {
        //
        //         }
        //
        //         @Override
        //         public void onFinished(AbsTask<Boolean> item) {
        //
        //         }
        //
        //         @Override
        //         public void onAllFinished() {
        //
        //         }
        //     }, mediumTask(), lowTask());
        //
        // } catch (Throwable throwable) {
        //     throwable.printStackTrace();
        // }

    }

    private String getCurrentName() {
        return Thread.currentThread().toString();
    }

    private Observable<Boolean> lowPriority() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                Log.e("newsalton", "lowPriority first:" + Thread.currentThread());
                emitter.onNext(true);
                Thread.sleep(3000);
                Log.e("newsalton", "lowPriority:" + Thread.currentThread());
            }
        }).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread());
    }

    private Observable<Boolean> mediumPriority() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                Log.e("newsalton", "mediumPriority first:" + Thread.currentThread());
                emitter.onNext(true);
                Thread.sleep(5000);
                Log.e("newsalton", "mediumPriority:" + Thread.currentThread());
            }
        });
    }

    private Observable<Boolean> highPriority() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                Log.e("newsalton", "highPriority:" + Thread.currentThread());
                emitter.onNext(true);
            }
        });
    }

    public AbsTask lowTask() {
        return new AbsTask<Boolean>() {

            @Override
            protected Boolean doBackground() throws Throwable {
                return null;
            }

            @Override
            protected void onSuccess(Boolean result) {

            }

            @Override
            protected void onError(Throwable ex, boolean isCallbackError) {

            }
        };
    }

    public AbsTask mediumTask() {
        return new AbsTask<Boolean>() {

            @Override
            protected Boolean doBackground() throws Throwable {
                return null;
            }

            @Override
            protected void onSuccess(Boolean result) {

            }

            @Override
            protected void onError(Throwable ex, boolean isCallbackError) {

            }
        };
    }
}
