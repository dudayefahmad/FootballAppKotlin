package com.ahmaddudayef.footballclub.ui.searchTeam

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/22/2018.
 */
class SearchTeamPresenter<V: SearchTeamMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), SearchTeamMvpPresenter<V> {

    override fun searchTeam(query: String?) {
        mvpView?.showLoading()
        compositeDisposable.add(
                dataManager.searchCLub(query)
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({ results ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            results.teams?.let { mvpView?.updateSearchTeam(it) }
                        }, { throwable ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            mvpView?.showMessage(throwable.message!!)
                            Timber.e(throwable.message)
                        })
        )
    }

}