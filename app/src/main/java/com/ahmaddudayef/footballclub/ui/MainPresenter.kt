package com.ahmaddudayef.footballclub.ui

import android.view.View
import com.ahmaddudayef.footballclub.model.TeamResponse
import com.ahmaddudayef.footballclub.network.ApiRepository
import com.ahmaddudayef.footballclub.network.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Ahmad Dudayef on 9/8/2018.
 */
class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getTeamList(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}