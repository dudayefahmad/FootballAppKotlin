package com.ahmaddudayef.footballclub.ui.player

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
class PlayerPresenter<V: PlayerMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), PlayerMvpPresenter<V> {

    override fun getPlayerList(teamId: String) {
        mvpView?.showLoading()
        compositeDisposable.add(
                dataManager.getAllPlayers(teamId)
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({ results ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            mvpView?.updateListPlayer(results.players)
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