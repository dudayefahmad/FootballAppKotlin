package com.ahmaddudayef.footballclub.data

import com.ahmaddudayef.footballclub.data.network.AppApiHelper
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
class AppDataManager @Inject constructor(
        private val appApiHelper: AppApiHelper
): DataManager {

    override fun getTeams(league: String): Observable<TeamResponse> {
        return appApiHelper.getTeams(league)
    }

    override fun getNextSchedule(league: String): Observable<Events> {
        return appApiHelper.getNextSchedule(league)
    }

    override fun getLastSchedule(league: String): Observable<Events> {
        return appApiHelper.getLastSchedule(league)
    }

    override fun getDetailTeam(idTeam: String): Single<TeamResponse> {
        return appApiHelper.getDetailTeam(idTeam)
    }

    override fun getAllLeagues(): Single<Leagues> {
        return appApiHelper.getAllLeagues()
    }

    override fun getMatchById(id: String): Flowable<Events> {
        return appApiHelper.getMatchById(id)
    }

    override fun getAllTeams(id: String): Flowable<TeamResponse> {
        return appApiHelper.getAllTeams(id)
    }

}