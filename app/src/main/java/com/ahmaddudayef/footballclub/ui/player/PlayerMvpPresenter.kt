package com.ahmaddudayef.footballclub.ui.player

import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
interface PlayerMvpPresenter<V: PlayerMvpView>: MvpPresenter<V> {
    fun getPlayerList(teamId: String)
}