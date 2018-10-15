package com.ahmaddudayef.footballclub.data.network

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.data.network.model.team.TeamResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
interface ApiHelper {
    fun getTeams(league: String): Observable<TeamResponse>

    fun getNextSchedule(league: String): Observable<Events>

    fun getLastSchedule(league: String): Observable<Events>

    fun getDetailTeam(idTeam: String): Single<TeamResponse>

    fun getAllLeagues(): Single<Leagues>

    fun getMatchById(id: String): Flowable<Events>
}