package com.ahmaddudayef.footballclub.ui.detailplayer

import android.content.Context
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
interface DetailPlayerMvpPresenter<V: DetailPlayerMvpView>: MvpPresenter<V> {
    fun addToFavorite(context: Context, player: Player)
    fun removeFavorite(context: Context, id: String)
}