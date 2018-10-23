package com.ahmaddudayef.footballclub.ui.favoriteplayer

import com.ahmaddudayef.footballclub.data.network.model.player.FavoritePlayers
import com.ahmaddudayef.footballclub.data.network.model.player.Players
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
interface FavoritePlayerMvpView : MvpView {
    fun showPlayerFavorite(players: FavoritePlayers)
}