package com.ahmaddudayef.footballclub.ui.searchTeam

import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 10/22/2018.
 */
interface SearchTeamMvpPresenter<V: SearchTeamMvpView>: MvpPresenter<V> {
    fun searchTeam(query: String?)
}