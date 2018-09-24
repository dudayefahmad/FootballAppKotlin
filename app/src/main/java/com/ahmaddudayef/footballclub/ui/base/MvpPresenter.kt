package com.ahmaddudayef.footballclub.ui.base

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
interface MvpPresenter<V: MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()
}