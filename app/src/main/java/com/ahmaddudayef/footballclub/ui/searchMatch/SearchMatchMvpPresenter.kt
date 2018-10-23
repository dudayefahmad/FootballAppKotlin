package com.ahmaddudayef.footballclub.ui.searchMatch

import com.ahmaddudayef.footballclub.ui.base.MvpPresenter

/**
 * Created by Ahmad Dudayef on 10/21/2018.
 */
interface SearchMatchMvpPresenter<V: SearchMatchMvpView> : MvpPresenter<V> {
    fun searchMatch(query: String?)
}