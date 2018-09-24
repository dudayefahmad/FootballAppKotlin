package com.ahmaddudayef.footballclub.data.network

import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.test.model.Team
import com.ahmaddudayef.footballclub.test.model.TeamResponse
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
}