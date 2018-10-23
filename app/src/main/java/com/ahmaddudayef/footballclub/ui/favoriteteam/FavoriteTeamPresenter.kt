package com.ahmaddudayef.footballclub.ui.favoriteteam

import android.content.Context
import android.util.Log
import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.TeamEntity
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
class FavoriteTeamPresenter<V: FavoriteTeamMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), FavoriteTeamMvpPresenter<V> {

    override fun getTeamFavorite(context: Context?): Single<List<TeamEntity>> {
        lateinit var favoriteList: List<TeamEntity>
        context?.database?.use {
            val result = select(TeamEntity.TABLE_TEAM)
            val favorite = result.parseList(classParser<TeamEntity>())
            favoriteList = favorite
        }
        return Single.just(favoriteList)
    }

    override fun getTeam(context: Context?) {
        compositeDisposable.add(
                getTeamFavorite(context)
                        .flattenAsFlowable { it }
                        .flatMap {
                            dataManager.getTeamById(it.teamId.toString())
                        }
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({results ->
                            if (!isViewAttached())
                                return@subscribe
                            Log.d("Masuk sini", "1")
                            mvpView?.showTeamFavorite(results)
                        }, { throwable ->
                            if (!isViewAttached())
                                return@subscribe
                            Log.d("Masuk sini", "2")
                            mvpView?.hideLoading()
                            mvpView?.showMessage(throwable.message!!)
                            Timber.e(throwable.message)
                        })
        )
    }

}