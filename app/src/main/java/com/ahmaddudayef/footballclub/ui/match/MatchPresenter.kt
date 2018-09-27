package com.ahmaddudayef.footballclub.ui.match

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
class MatchPresenter<V: MatchMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
): BasePresenter<V>(dataManager, compositeDisposable), MatchMvpPresenter<V>