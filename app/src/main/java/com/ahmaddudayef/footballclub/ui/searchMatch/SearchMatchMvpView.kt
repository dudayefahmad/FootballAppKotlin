package com.ahmaddudayef.footballclub.ui.searchMatch

import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 10/21/2018.
 */
interface SearchMatchMvpView : MvpView {
    fun updateSearchMatch(listMatch: MutableList<EventsItem>)
}