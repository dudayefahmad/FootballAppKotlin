package com.ahmaddudayef.footballclub.ui.base

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
interface MvpView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String)
    fun showError(message: String)
}