package com.ahmaddudayef.footballclub.ui.detail

import android.content.Context
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 9/21/2018.
 */
interface DetailMatchMvpPresenter<V: DetailMatchMvpView>: MvpPresenter<V> {
    fun getTeamsBadge(homeBadge: String, awayBadge: String)
    fun addToFavorite(context: Context, match: EventsItem)
    fun removeFromFavorite(context: Context, id: String)
}