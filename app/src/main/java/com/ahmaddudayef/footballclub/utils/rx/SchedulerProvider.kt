package com.ahmaddudayef.footballclub.utils.rx

import io.reactivex.Scheduler

/**
 * Created by Ahmad Dudayef on 10/11/2018.
 */
interface SchedulerProvider {
    fun mainThread(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler
}