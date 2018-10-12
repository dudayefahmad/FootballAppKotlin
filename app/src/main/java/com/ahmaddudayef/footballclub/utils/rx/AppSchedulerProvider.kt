package com.ahmaddudayef.footballclub.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ahmad Dudayef on 10/12/2018.
 */
open class AppSchedulerProvider : SchedulerProvider {
    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun trampoline(): Scheduler = Schedulers.trampoline()

    override fun newThread(): Scheduler = Schedulers.newThread()

    override fun io(): Scheduler = Schedulers.io()

}