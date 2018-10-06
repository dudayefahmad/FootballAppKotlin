package com.ahmaddudayef.footballclub.ui.favorite

import android.content.Context
import android.util.Log
import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.MatchEntity

import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
class FavoritePresenter<V: FavoriteMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable
): BasePresenter<V>(dataManager, compositeDisposable), FavoriteMvpPresenter<V>{

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
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
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