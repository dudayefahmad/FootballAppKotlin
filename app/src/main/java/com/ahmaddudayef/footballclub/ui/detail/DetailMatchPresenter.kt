package com.ahmaddudayef.footballclub.ui.detail

import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.test.model.TeamResponse
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/21/2018.
 */
class DetailMatchPresenter<V: DetailMatchMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
): BasePresenter<V>(dataManager, compositeDisposable), DetailMatchMvpPresenter<V> {

    override fun getTeamsBadge(homeBadge: String, awayBadge: String) {
        mvpView?.showLoading()
        compositeDisposable.add(
                Single.zip(
                        dataManager.getDetailTeam(homeBadge),
                        dataManager.getDetailTeam(awayBadge),
                        BiFunction<TeamResponse, TeamResponse, List<String>> { t1, t2 ->
                            listOf(t1.teams[0].teamBadge!!, t2.teams[0].teamBadge!!)
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ logoClub ->
                            run {
                                if (!isViewAttached())
                                    return@subscribe
                                mvpView?.hideLoading()
                                if (logoClub != null)
                                    mvpView?.showDetailMatch(logoClub[0], logoClub[1])
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