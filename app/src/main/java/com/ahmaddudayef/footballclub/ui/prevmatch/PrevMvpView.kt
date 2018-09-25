package com.ahmaddudayef.footballclub.ui.prevmatch

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 9/18/2018.
 */
interface PrevMvpView : MvpView {
    fun updateList(listTeam: MutableList<EventsItem>)
    fun updateLeagueid(leagues: Leagues)
}