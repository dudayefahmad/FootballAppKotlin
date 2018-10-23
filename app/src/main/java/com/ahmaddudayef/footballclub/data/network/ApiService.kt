package com.ahmaddudayef.footballclub.data.network

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.player.FavoritePlayers
import com.ahmaddudayef.footballclub.data.network.model.player.Players
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.data.network.model.searchmatch.SearchedMatches
import com.ahmaddudayef.footballclub.data.network.model.team.TeamResponse
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

    @GET("lookupevent.php")
    fun getMatchById(@Query("id") id: String): Flowable<Events>

    @GET("lookupteam.php")
    fun getTeamById(@Query("id") id: String): Flowable<TeamResponse>

    @GET("lookupplayer.php?")
    fun getPlayerById(@Query("id") id: String): Flowable<FavoritePlayers>

    @GET("lookup_all_teams.php")
    fun getAllTeams(@Query("id") id:String) : Flowable<TeamResponse>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") id:String) : Flowable<Players>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String?) : Flowable<SearchedMatches>

    @GET("searchteams.php")
    fun searchClub(@Query("t") query: String?) : Flowable<TeamResponse>
}