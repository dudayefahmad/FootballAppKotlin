package com.ahmaddudayef.footballclub.ui.favorite

import android.content.Context
import com.ahmaddudayef.footballclub.data.db.entities.MatchEntity
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.ui.base.MvpPresenter
import io.reactivex.Single

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
interface FavoriteMvpPresenter<V: FavoriteMvpView>: MvpPresenter<V>{
    fun getMatchFavorite(context: Context?): Single<List<MatchEntity>>
    fun getNextMatch(context: Context?)
}