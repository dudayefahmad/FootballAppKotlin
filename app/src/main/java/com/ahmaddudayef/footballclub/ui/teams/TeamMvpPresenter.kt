package com.ahmaddudayef.footballclub.ui.teams

import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
interface TeamMvpPresenter<V: TeamMvpView>: MvpPresenter<V> {
    fun getTeamList(leagueId: String)
    fun getAllLeagues()
}