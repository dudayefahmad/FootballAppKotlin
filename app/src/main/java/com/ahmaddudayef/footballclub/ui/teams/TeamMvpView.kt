package com.ahmaddudayef.footballclub.ui.teams

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
interface TeamMvpView : MvpView {
    fun updateListTeam(listTeam: MutableList<Team>)
    fun updateLeagueId(leagues: Leagues)
}