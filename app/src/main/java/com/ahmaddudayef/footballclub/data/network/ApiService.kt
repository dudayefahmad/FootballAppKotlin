package com.ahmaddudayef.footballclub.data.network

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.test.model.TeamResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
interface ApiService {
    @GET("search_all_teams.php")
    fun getTeams(@Query("l") leagueId: String): Observable<TeamResponse>

    @GET("eventsnextleague.php")
    fun getNextSchedules(@Query("id") leagueId: String): Observable<Events>

    @GET("eventspastleague.php")
    fun getLastSchedules(@Query("id") leagueId: String): Observable<Events>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String): Single<TeamResponse>

    @GET("all_leagues.php")
    fun getAllLeagues() : Single<Leagues>
}