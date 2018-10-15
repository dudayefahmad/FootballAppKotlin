package com.ahmaddudayef.footballclub.data.network

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.data.network.model.team.TeamResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class AppApiHelper @Inject constructor(
        private val apiService: ApiService
): ApiHelper {

    override fun getTeams(league: String): Observable<TeamResponse> {
        return apiService.getTeams(league)
    }

    override fun getNextSchedule(league: String): Observable<Events> {
        return apiService.getNextSchedules(league)
    }

    override fun getLastSchedule(league: String): Observable<Events> {
        return apiService.getLastSchedules(league)
    }

    override fun getDetailTeam(idTeam: String): Single<TeamResponse> {
        return apiService.getDetailTeam(idTeam)
    }

    override fun getAllLeagues(): Single<Leagues> {
        return apiService.getAllLeagues()
    }

    override fun getMatchById(id: String): Flowable<Events> {
        return apiService.getMatchById(id)
    }

}