package com.ahmaddudayef.footballclub.ui.teams

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
class TeamPresenter<V: TeamMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), TeamMvpPresenter<V> {

    override fun getTeamList(leagueId: String) {
        mvpView?.showLoading()
        compositeDisposable.add(
                dataManager.getAllTeams(leagueId)
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({ results ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            results.teams?.let { mvpView?.updateListTeam(it) }
                        }, { throwable ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            mvpView?.showMessage(throwable.message!!)
                            Timber.e(throwable.message)
                        })
        )
    }

    override fun getAllLeagues() {
        mvpView?.showLoading()
        compositeDisposable.add(
                dataManager.getAllLeagues()
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({ results->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            mvpView?.updateLeagueId(results)
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