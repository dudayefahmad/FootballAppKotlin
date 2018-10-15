package com.ahmaddudayef.footballclub.ui.home

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.AppSchedulerProvider
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/16/2018.
 */
class HomePresenter<V: HomeMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), HomeMvpPresenter<V>