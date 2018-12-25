package com.salton123.app

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.CountDownLatch

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/24 上午11:20
 * ModifyTime: 上午11:20
 * Description:优化Application加载顺序，加载项按高中低优先级并发加载
 */

//2018-12-25 22:40:26.714 27767-27767/? E/ApplicationBase: step one:main
//2018-12-25 22:40:26.837 27767-27797/? E/InitializeLoader: highPriority start:Thread[RxNewThreadScheduler-1,5,main]
//2018-12-25 22:40:26.838 27767-27798/? E/InitializeLoader: mediumPriority start:Thread[RxNewThreadScheduler-2,5,main]
//2018-12-25 22:40:26.839 27767-27799/? E/InitializeLoader: lowPriority start:Thread[RxNewThreadScheduler-3,5,main]
//2018-12-25 22:40:27.133 27767-27797/com.salton123.saltonframeworkdemo E/ApplicationBase: step two high:RxNewThreadScheduler-1
//2018-12-25 22:40:27.135 27767-27797/com.salton123.saltonframeworkdemo E/InitializeLoader: highPriority:endThread[RxNewThreadScheduler-1,5,main]
//2018-12-25 22:40:27.135 27767-27767/com.salton123.saltonframeworkdemo E/ApplicationBase: step three:main
//2018-12-25 22:40:27.633 27767-27798/com.salton123.saltonframeworkdemo E/ApplicationBase: step two medium:RxNewThreadScheduler-2
//2018-12-25 22:40:27.634 27767-27799/com.salton123.saltonframeworkdemo E/ApplicationBase: step two low:RxNewThreadScheduler-3
//2018-12-25 22:40:27.637 27767-27798/com.salton123.saltonframeworkdemo E/InitializeLoader: mediumPriority:endThread[RxNewThreadScheduler-2,5,main]
//2018-12-25 22:40:27.650 27767-27799/com.salton123.saltonframeworkdemo E/InitializeLoader: lowPriority:endThread[RxNewThreadScheduler-3,5,main]main
//cost:0.936

//2018-12-25 22:41:19.914 27916-27916/com.salton123.saltonframeworkdemo E/ApplicationBase: step one:main
//2018-12-25 22:41:20.027 27916-27936/com.salton123.saltonframeworkdemo E/InitializeLoader: highPriority start:Thread[RxNewThreadScheduler-1,5,main]
//2018-12-25 22:41:20.028 27916-27937/com.salton123.saltonframeworkdemo E/InitializeLoader: mediumPriority start:Thread[RxNewThreadScheduler-2,5,main]
//2018-12-25 22:41:20.028 27916-27938/com.salton123.saltonframeworkdemo E/InitializeLoader: lowPriority start:Thread[RxNewThreadScheduler-3,5,main]
//2018-12-25 22:41:20.528 27916-27936/com.salton123.saltonframeworkdemo E/ApplicationBase: step two high:RxNewThreadScheduler-1
//2018-12-25 22:41:20.529 27916-27936/com.salton123.saltonframeworkdemo E/InitializeLoader: highPriority:endThread[RxNewThreadScheduler-1,5,main]
//2018-12-25 22:41:20.529 27916-27916/com.salton123.saltonframeworkdemo E/ApplicationBase: step three:main
//2018-12-25 22:41:21.028 27916-27937/com.salton123.saltonframeworkdemo E/ApplicationBase: step two medium:RxNewThreadScheduler-2
//2018-12-25 22:41:21.029 27916-27938/com.salton123.saltonframeworkdemo E/ApplicationBase: step two low:RxNewThreadScheduler-3
//2018-12-25 22:41:21.032 27916-27937/com.salton123.saltonframeworkdemo E/InitializeLoader: mediumPriority:endThread[RxNewThreadScheduler-2,5,main]
//2018-12-25 22:41:21.043 27916-27938/com.salton123.saltonframeworkdemo E/InitializeLoader: lowPriority:endThread[RxNewThreadScheduler-3,5,main]
//cost: 1.129

//2018-12-25 22:43:15.897 28278-28278/? E/ApplicationBase: step one:main
//2018-12-25 22:43:16.034 28278-28305/? E/InitializeLoader: lowPriority start:Thread[RxComputationThreadPool-4,5,main]
//2018-12-25 22:43:16.034 28278-28303/? E/InitializeLoader: highPriority start:Thread[RxComputationThreadPool-2,5,main]
//2018-12-25 22:43:16.034 28278-28304/? E/InitializeLoader: mediumPriority start:Thread[RxComputationThreadPool-3,5,main]
//2018-12-25 22:43:16.535 28278-28303/com.salton123.saltonframeworkdemo E/ApplicationBase: step two high:RxComputationThreadPool-2
//2018-12-25 22:43:16.536 28278-28303/com.salton123.saltonframeworkdemo E/InitializeLoader: highPriority:endThread[RxComputationThreadPool-2,5,main]
//2018-12-25 22:43:16.537 28278-28278/com.salton123.saltonframeworkdemo E/ApplicationBase: step three:main
//2018-12-25 22:43:17.035 28278-28304/com.salton123.saltonframeworkdemo E/ApplicationBase: step two medium:RxComputationThreadPool-3
//2018-12-25 22:43:17.035 28278-28305/com.salton123.saltonframeworkdemo E/ApplicationBase: step two low:RxComputationThreadPool-4
//2018-12-25 22:43:17.039 28278-28304/com.salton123.saltonframeworkdemo E/InitializeLoader: mediumPriority:endThread[RxComputationThreadPool-3,5,main]
//2018-12-25 22:43:17.050 28278-28305/com.salton123.saltonframeworkdemo E/InitializeLoader: lowPriority:endThread[RxComputationThreadPool-4,5,main]
// cost:1.153
object InitializeLoader {
    private val TAG = "InitializeLoader"
    private lateinit var mPriority: IFutureTaskPriority
    private lateinit var mLatch: CountDownLatch //定义一个CountDownLatch只保证高优先级初始化操作执行完后主线程继续执行

    fun init(priority: IFutureTaskPriority, latch: CountDownLatch): Observable<Boolean> {
        this.mPriority = priority
        this.mLatch = latch
        return highPriority().zipWith(mediumPriority(), BiFunction<Boolean, Boolean, Boolean> { t1, t2 ->
            t1 and t2
        }).zipWith(lowPriority(), BiFunction<Boolean, Boolean, Boolean> { t1, t2 ->
            t1 and t2
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
//        return Observable.zip(
//                highPriority(),
//                mediumPriority(),
//                lowPriority(),
//                Function3<Boolean, Boolean, Boolean, Boolean> { t1, t2, t3 ->
//                    Log.e(TAG, "t1=$t1, t2=$t2, t3=$t3")
//                    t1 and t2 and t3
//                })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun lowPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e(TAG, "lowPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.lowPriority())
            Log.e(TAG, "lowPriority:end" + Thread.currentThread())
            emitter.onComplete()
        }).observeOn(Schedulers.computation()).subscribeOn(Schedulers.computation())
    }

    private fun mediumPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e(TAG, "mediumPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.mediumPriority())
            Log.e(TAG, "mediumPriority:end" + Thread.currentThread())
            emitter.onComplete()
        }).observeOn(Schedulers.computation()).subscribeOn(Schedulers.computation())
    }

    private fun highPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e(TAG, "highPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.highPriority())
            Log.e(TAG, "highPriority:end" + Thread.currentThread())
            mLatch.countDown()
            emitter.onComplete()
        }).observeOn(Schedulers.computation()).subscribeOn(Schedulers.computation())
    }
}