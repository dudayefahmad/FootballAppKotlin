package com.ahmaddudayef.footballclub.ui.favoritematch

import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
interface FavoriteMatchMvpView : MvpView {
    fun showMatchFavorite(matches: Events)
}