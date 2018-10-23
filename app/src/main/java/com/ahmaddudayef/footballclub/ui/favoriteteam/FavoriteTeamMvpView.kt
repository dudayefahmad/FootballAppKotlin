package com.ahmaddudayef.footballclub.ui.favoriteteam

import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.data.network.model.team.TeamResponse
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
interface FavoriteTeamMvpView : MvpView {
    fun showTeamFavorite(teams: TeamResponse)
}