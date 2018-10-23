package com.ahmaddudayef.footballclub.ui.searchTeam

import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 10/22/2018.
 */
interface SearchTeamMvpView : MvpView {
    fun updateSearchTeam(listTeam: MutableList<Team>)
}