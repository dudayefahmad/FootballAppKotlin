package com.ahmaddudayef.footballclub.ui.home

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/16/2018.
 */
class HomePresenter<V: HomeMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
): BasePresenter<V>(dataManager, compositeDisposable), HomeMvpPresenter<V> {


}