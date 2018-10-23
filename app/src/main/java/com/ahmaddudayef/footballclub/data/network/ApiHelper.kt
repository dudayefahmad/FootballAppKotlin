package com.ahmaddudayef.footballclub.data.network

import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.player.FavoritePlayers
import com.ahmaddudayef.footballclub.data.network.model.player.Player
import com.ahmaddudayef.footballclub.data.network.model.player.Players
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.data.network.model.searchmatch.SearchedMatches
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

    fun getTeamById(id: String): Flowable<TeamResponse>

    fun getPlayerById(id: String): Flowable<FavoritePlayers>

    fun getAllTeams(id: String): Flowable<TeamResponse>

    fun getAllPlayers(id: String): Flowable<Players>

    fun searchMatches(e: String?): Flowable<SearchedMatches>

    fun searchCLub(t: String?): Flowable<TeamResponse>
}