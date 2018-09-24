package com.ahmaddudayef.footballclub.ui.detail

import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 9/21/2018.
 */
interface DetailMatchMvpPresenter<V: DetailMatchMvpView>: MvpPresenter<V> {
    fun getTeamsBadge(homeBadge: String, awayBadge: String)
}