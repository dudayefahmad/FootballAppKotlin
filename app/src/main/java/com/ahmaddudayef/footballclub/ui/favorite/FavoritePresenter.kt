package com.ahmaddudayef.footballclub.ui.favorite

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.ui.home.HomeMvpPresenter
import com.ahmaddudayef.footballclub.ui.home.HomeMvpView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
class FavoritePresenter<V: FavoriteMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
): BasePresenter<V>(dataManager, compositeDisposable), FavoriteMvpPresenter<V>