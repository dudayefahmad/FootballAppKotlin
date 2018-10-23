package com.ahmaddudayef.footballclub.ui.favoriteteam


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.data.network.model.team.TeamResponse
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : BaseFragment(), FavoriteTeamMvpView {

    @Inject
    lateinit var presenterTeam: FavoriteTeamMvpPresenter<FavoriteTeamMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    private var list_teams: MutableList<Team> = mutableListOf()
    private var adapter = FavoriteTeamAdapter(list_teams)

    companion object {
        fun newInstance() = FavoriteTeamFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        presenterTeam.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        listFavTeam.layoutManager = gridLayoutManager
        listFavTeam.adapter = adapter
        swipeRefresh.setOnRefreshListener { getFavoriteFromDb() }
        swipeRefresh.post {
            swipeRefresh.isRefreshing = true
            getFavoriteFromDb()
        }
    }

    private fun getFavoriteFromDb() {
        list_teams.clear()
        presenterTeam.getTeam(context)
        swipeRefresh.isRefreshing = false
    }

    override fun showTeamFavorite(teams: TeamResponse) {
        swipeRefresh.isRefreshing = false
        list_teams.add(teams.teams!![0])
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenterTeam.onDetach()
        super.onDestroy()
    }
}
