package com.ahmaddudayef.footballclub.ui.prevmatch

import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 9/18/2018.
 */
interface PrevMvpPresenter<V: PrevMvpView>: MvpPresenter<V> {
    fun getPrevScheduleList(leagueId: String)
    fun getAllLeagues()
}