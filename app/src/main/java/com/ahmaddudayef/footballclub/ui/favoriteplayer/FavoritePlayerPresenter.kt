package com.ahmaddudayef.footballclub.ui.favoriteplayer

import android.content.Context
import android.util.Log
import com.ahmaddudayef.footballclub.data.DataManager
import com.ahmaddudayef.footballclub.data.db.database
import com.ahmaddudayef.footballclub.data.db.entities.PlayerEntity
import com.ahmaddudayef.footballclub.ui.base.BasePresenter
import com.ahmaddudayef.footballclub.ui.favorites.FavoriteMvpPresenter
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
class FavoritePlayerPresenter<V: FavoritePlayerMvpView> @Inject constructor(
        private val dataManager: DataManager,
        private val compositeDisposable: CompositeDisposable,
        private val subscriber: SchedulerProvider
): BasePresenter<V>(dataManager, compositeDisposable, subscriber), FavoritePlayerMvpPresenter<V> {

    override fun getPlayerFavorite(context: Context?): Single<List<PlayerEntity>> {
        lateinit var favoriteList: List<PlayerEntity>
        context?.database?.use {
            val result = select(PlayerEntity.TABLE_PLAYER)
            val favorite = result.parseList(classParser<PlayerEntity>())
            favoriteList = favorite
        }
        return Single.just(favoriteList)
    }

    override fun getPlayer(context: Context?) {
        compositeDisposable.add(
                getPlayerFavorite(context)
                        .flattenAsFlowable { it }
                        .flatMap {
                            dataManager.getPlayerById(it.playerId.toString())
                        }
                        .subscribeOn(subscriber.io())
                        .observeOn(subscriber.mainThread())
                        .subscribe({ results ->
                            if (!isViewAttached())
                                return@subscribe
                            Log.d("Masuk sini", "1")
                            mvpView?.showPlayerFavorite(results)
                        },  { throwable ->
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