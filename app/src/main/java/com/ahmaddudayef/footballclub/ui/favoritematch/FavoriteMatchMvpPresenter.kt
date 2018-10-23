package com.ahmaddudayef.footballclub.ui.favoritematch

import android.content.Context
import com.ahmaddudayef.footballclub.data.db.entities.MatchEntity
import com.ahmaddudayef.footballclub.ui.base.MvpPresenter
import io.reactivex.Single

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
interface FavoriteMatchMvpPresenter<V: FavoriteMatchMvpView>: MvpPresenter<V>{
    fun getMatchFavorite(context: Context?): Single<List<MatchEntity>>
    fun getNextMatch(context: Context?)
}