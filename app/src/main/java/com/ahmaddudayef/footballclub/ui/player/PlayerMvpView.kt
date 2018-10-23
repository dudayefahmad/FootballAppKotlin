package com.ahmaddudayef.footballclub.ui.player

import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
interface PlayerMvpView : MvpView {
    fun updateListPlayer(listPlayer: MutableList<Player>)
}