package com.ahmaddudayef.footballclub.ui.favoritematch

import android.content.Context
import android.util.Log
import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.MatchEntity

import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
class FavoriteMatchPresenter<V: FavoriteMatchMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), FavoriteMatchMvpPresenter<V>{

    override fun getMatchFavorite(context: Context?): Single<List<MatchEntity>> {
        lateinit var favoriteList: List<MatchEntity>
        context?.database?.use {
            val result = select(MatchEntity.TABLE_MATCH)
            val favorite = result.parseList(classParser<MatchEntity>())
            favoriteList = favorite
        }
        return Single.just(favoriteList)
    }

    override fun getNextMatch(context: Context?) {
        compositeDisposable.add(
                getMatchFavorite(context)
                        .flattenAsFlowable { it }
                        .flatMap {
                            dataManager.getMatchById(it.eventId.toString())
                        }
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({results ->
                            if (!isViewAttached())
                                return@subscribe
                            Log.d("Masuk sini", "1")

                            mvpView?.showMatchFavorite(results)
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