package com.ahmaddudayef.footballclub.ui.favoriteteam

import android.content.Context
import com.ahmaddudayef.footballclub.data.db.entities.TeamEntity
import com.ahmaddudayef.footballclub.ui.base.MvpPresenter
import io.reactivex.Single

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
interface FavoriteTeamMvpPresenter<V: FavoriteTeamMvpView>: MvpPresenter<V> {
    fun getTeamFavorite(context: Context?): Single<List<TeamEntity>>
    fun getTeam(context: Context?)
}