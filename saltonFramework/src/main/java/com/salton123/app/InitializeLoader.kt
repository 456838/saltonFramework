package com.salton123.app

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.CountDownLatch

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/24 上午11:20
 * ModifyTime: 上午11:20
 * Description:优化Application加载顺序，加载项按高中低优先级并发加载
 */

object InitializeLoader {
    private val TAG = "InitializeLoader"
    private lateinit var mPriority: IFutureTaskPriority
    private lateinit var mLatch: CountDownLatch //定义一个CountDownLatch只保证高优先级初始化操作执行完后主线程继续执行

    fun init(priority: IFutureTaskPriority, latch: CountDownLatch): Observable<Boolean> {
        this.mPriority = priority
        this.mLatch = latch
        return Observable.zip(
                highPriority(),
                mediumPriority(),
                lowPriority(),
                Function3<Boolean, Boolean, Boolean, Boolean> { t1, t2, t3 ->
                    Log.e(TAG, "t1=$t1, t2=$t2, t3=$t3")
                    t1 and t2 and t3
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun lowPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e(TAG, "lowPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.lowPriority())
            Log.e(TAG, "lowPriority:end" + Thread.currentThread())
            mLatch.countDown()
            emitter.onComplete()
        }).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread())
    }

    private fun mediumPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e(TAG, "mediumPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.mediumPriority())
            Log.e(TAG, "mediumPriority:end" + Thread.currentThread())
            emitter.onComplete()
        }).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread())
    }

    private fun highPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e(TAG, "highPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.highPriority())
            Log.e(TAG, "highPriority:end" + Thread.currentThread())
            emitter.onComplete()
        }).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread())
    }
}