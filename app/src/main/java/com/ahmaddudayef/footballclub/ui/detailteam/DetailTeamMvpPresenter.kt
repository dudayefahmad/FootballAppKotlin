package com.ahmaddudayef.footballclub.ui.detailteam

import android.content.Context
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 10/18/2018.
 */
interface DetailTeamMvpPresenter<V: DetailTeamMvpView>: MvpPresenter<V> {
    fun addToFavorite(context: Context, team: Team)
    fun removeFavorite(context: Context, id: String)
}