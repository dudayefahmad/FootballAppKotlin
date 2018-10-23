package com.ahmaddudayef.footballclub.ui.teams


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamActivity
import com.ahmaddudayef.footballclub.ui.searchMatch.SearchMatchActivity
import com.ahmaddudayef.footballclub.ui.searchTeam.SearchTeamActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : BaseFragment(), TeamMvpView, TeamAdapter.Callback {

    @Inject
    lateinit var presenter: TeamMvpPresenter<TeamMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var teamAdapter: TeamAdapter

    private var menuItem: Menu? = null

    companion object {
        fun newInstance() = TeamFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        presenter.onAttach(this)
        teamAdapter.setCallback(this)
        return view
    }

    override fun setUp(view: View) {
        presenter.getAllLeagues()
        presenter.getTeamList("4328")

        team_list.layoutManager = gridLayoutManager
        team_list.setHasFixedSize(true)
        team_list.adapter = teamAdapter
    }

    override fun updateLeagueId(leagues: Leagues) {
        val strLeagues = leagues.leagues.map { it.strLeague }
        val leaguesId = leagues.leagues.map { it.idLeague }
        setDropdownLeague(spinnerLeague, strLeagues, object : TeamFragment.SpinnerListener {
            override fun getLeague(position: Int) {
                teamAdapter.clearItem()
                presenter.getTeamList(leaguesId[position])
            }
        })
    }

    private fun setDropdownLeague(spinner: Spinner,  item: List<String>, listener: SpinnerListener){
        spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, item)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                listener.getLeague(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>) = Unit
        }
    }

    interface SpinnerListener {
        fun getLeague(position: Int)
    }

    override fun updateListTeam(listTeam: MutableList<Team>) {
        team_list.visibility = VISIBLE
        teamAdapter.addItems(listTeam)
    }

    override fun onTeamClick(team: Team) {
        startActivity<DetailTeamActivity>("team_detail" to team)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        menuItem = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSearch -> {
                val searchView = menuItem?.findItem(R.id.actionSearch)?.actionView as SearchView?

                searchView?.queryHint = "Search Teams"

                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val intent = Intent(context, SearchTeamActivity::class.java)
                        intent.putExtra("query", query)
                        startActivity(intent)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }


}
