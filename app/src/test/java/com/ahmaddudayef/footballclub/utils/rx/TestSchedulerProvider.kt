package com.ahmaddudayef.footballclub.utils.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

/**
 * Created by Ahmad Dudayef on 10/12/2018.
 */
class TestSchedulerProvider(var mTestScheduler: TestScheduler) : AppSchedulerProvider() {
    override fun mainThread(): Scheduler = mTestScheduler

    override fun computation(): Scheduler = mTestScheduler

    override fun trampoline(): Scheduler = mTestScheduler

    override fun newThread(): Scheduler = mTestScheduler

    override fun io(): Scheduler = mTestScheduler

}