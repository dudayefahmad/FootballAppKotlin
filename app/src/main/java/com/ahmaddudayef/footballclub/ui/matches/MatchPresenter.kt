package com.ahmaddudayef.footballclub.ui.matches

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
class MatchPresenter<V: MatchMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), MatchMvpPresenter<V> {

}