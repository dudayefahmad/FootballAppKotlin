package com.ahmaddudayef.footballclub.ui.detail

import com.ahmaddudayef.footballclub.ui.base.MvpView

/**
 * Created by Ahmad Dudayef on 9/21/2018.
 */
interface DetailMatchMvpView : MvpView {
    fun showDetailMatch(homeBadge: String, awayBadge: String)
}