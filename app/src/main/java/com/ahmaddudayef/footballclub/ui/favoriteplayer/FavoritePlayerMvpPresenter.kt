package com.ahmaddudayef.footballclub.ui.favoriteplayer

import android.content.Context
import com.ahmaddudayef.footballclub.data.db.entities.PlayerEntity
import com.ahmaddudayef.footballclub.ui.base.MvpPresenter
import io.reactivex.Single

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
interface FavoritePlayerMvpPresenter<V: FavoritePlayerMvpView>: MvpPresenter<V> {
    fun getPlayerFavorite(context: Context?): Single<List<PlayerEntity>>
    fun getPlayer(context: Context?)
}