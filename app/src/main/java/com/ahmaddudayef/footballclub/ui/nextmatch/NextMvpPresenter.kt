package com.ahmaddudayef.footballclub.ui.nextmatch

import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
interface NextMvpPresenter<V: NextMvpView>: MvpPresenter<V> {
    fun getNextScheduleList(leagueId: String)
    fun getAllLeagues()
}