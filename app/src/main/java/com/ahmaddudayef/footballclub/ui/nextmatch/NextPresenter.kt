package com.ahmaddudayef.footballclub.ui.nextmatch

import android.util.Log
import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.AppSchedulerProvider
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class NextPresenter<V: NextMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: AppSchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), NextMvpPresenter<V> {

    override fun getNextScheduleList(leagueId: String) {
        mvpView?.showLoading()
        compositeDisposable.add(
                dataManager.getNextSchedule(leagueId)
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({ results ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            if (results.events != null){
                                var data = results.events!!.size
                                Log.d("Data size = ", data.toString())
                                mvpView?.updateList(results.events!!)
                            }
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
                        .subscribe({ results ->
                            if (!isViewAttached())
                                return@subscribe
                            mvpView?.hideLoading()
                            if (results.leagues != null){
                                mvpView?.updateLeagueid(results)
                            }
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