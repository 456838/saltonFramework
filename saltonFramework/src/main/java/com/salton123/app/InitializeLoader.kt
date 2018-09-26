package com.salton123.app

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/24 上午11:20
 * ModifyTime: 上午11:20
 * Description:优化Application加载顺序，加载项按高中低优先级并发加载
 */

/*
09-26 16:12:53.596 14697-14742/com.salton123.saltonframeworkdemo E/newsalton: lowPriority start:Thread[RxNewThreadScheduler-2,5,main]
09-26 16:12:53.599 14697-14743/com.salton123.saltonframeworkdemo E/newsalton: mediumPriority start:Thread[RxNewThreadScheduler-3,5,main]
09-26 16:12:53.600 14697-14744/com.salton123.saltonframeworkdemo E/newsalton: highPriority start:Thread[RxNewThreadScheduler-4,5,main]

09-26 16:12:53.603 14697-14742/com.salton123.saltonframeworkdemo E/newsalton: lowPriority:Thread[RxNewThreadScheduler-2,5,main]
09-26 16:12:53.605 14697-14742/com.salton123.saltonframeworkdemo E/newsalton: lowPriority:endThread[RxNewThreadScheduler-2,5,main]

09-26 16:12:56.600 14697-14744/com.salton123.saltonframeworkdemo E/newsalton: highPriority:Thread[RxNewThreadScheduler-4,5,main]
09-26 16:12:56.601 14697-14744/com.salton123.saltonframeworkdemo E/newsalton: highPriority:endThread[RxNewThreadScheduler-4,5,main]

09-26 16:12:58.601 14697-14743/com.salton123.saltonframeworkdemo E/newsalton: mediumPriority:Thread[RxNewThreadScheduler-3,5,main]
09-26 16:12:58.602 14697-14743/com.salton123.saltonframeworkdemo E/newsalton: mediumPriority:endThread[RxNewThreadScheduler-3,5,main]

09-26 16:12:58.605 14697-14895/com.salton123.saltonframeworkdemo E/newsalton: t1, t2, t3
09-26 16:12:58.609 14697-14697/com.salton123.saltonframeworkdemo E/newsalton: InitializeLoader onNext: Thread[main,5,main]
09-26 16:12:58.610 14697-14697/com.salton123.saltonframeworkdemo E/newsalton: InitializeLoader onComplete:Thread[main,5,main]
*/
object InitializeLoader {
    private lateinit var mPriority: IFutureTaskPriority
    fun init(priority: IFutureTaskPriority): Observable<Boolean> {
        this.mPriority = priority
       return Observable.zip(
            highPriority(),
            mediumPriority(),
            lowPriority(),
            Function3<Boolean, Boolean, Boolean, Boolean> { t1, t2, t3 ->
                Log.e("newsalton", "t1=$t1, t2=$t2, t3=$t3")
                t1 and t2 and t3
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ Log.e("newsalton", "InitializeLoader onNext: " + Thread.currentThread()) }, {
//                Log.e("newsalton", "InitializeLoader onError:" + Thread.currentThread())
//            }, { Log.e("newsalton", "InitializeLoader onComplete:" + Thread.currentThread()) })
    }

    private fun lowPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e("newsalton", "lowPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.lowPriority())
            Log.e("newsalton", "lowPriority:end" + Thread.currentThread())
            emitter.onComplete()
        }).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread())
    }

    private fun mediumPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e("newsalton", "mediumPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.mediumPriority())
            Log.e("newsalton", "mediumPriority:end" + Thread.currentThread())
            emitter.onComplete()
        }).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread())
    }

    private fun highPriority(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> { emitter ->
            Log.e("newsalton", "highPriority start:" + Thread.currentThread())
            emitter.onNext(mPriority.highPriority())
            Log.e("newsalton", "highPriority:end" + Thread.currentThread())
            emitter.onComplete()
        }).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread())
    }
}