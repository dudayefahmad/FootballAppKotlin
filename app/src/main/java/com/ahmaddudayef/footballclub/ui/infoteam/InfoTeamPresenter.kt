package com.ahmaddudayef.footballclub.ui.infoteam

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.ui.nextmatch.NextPresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
class InfoTeamPresenter<V: InfoTeamMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), InfoTeamMvpPresenter<V>