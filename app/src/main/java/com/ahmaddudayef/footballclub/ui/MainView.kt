package com.ahmaddudayef.footballclub.ui

import com.ahmaddudayef.footballclub.model.Team

/**
 * Created by Ahmad Dudayef on 9/8/2018.
 */
interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}