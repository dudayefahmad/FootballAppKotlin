package com.ahmaddudayef.footballclub.ui.nextmatch

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
interface NextMvpView : MvpView {
    fun updateList(listTeam: MutableList<EventsItem>)
    fun updateLeagueid(leagues: Leagues)
}