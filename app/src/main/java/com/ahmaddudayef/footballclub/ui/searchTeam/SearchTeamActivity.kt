package com.ahmaddudayef.footballclub.ui.searchTeam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.team.Team
import com.ahmaddudayef.footballclub.ui.about.AboutActivity
import com.ahmaddudayef.footballclub.ui.base.BaseActivity
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamActivity
import com.ahmaddudayef.footballclub.ui.teams.TeamAdapter
import com.franmontiel.attributionpresenter.AttributionPresenter
import com.franmontiel.attributionpresenter.entities.Attribution
import com.franmontiel.attributionpresenter.entities.Library
import com.franmontiel.attributionpresenter.entities.License
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SearchTeamActivity : BaseActivity(), SearchTeamMvpView, TeamAdapter.Callback {

    @Inject
    lateinit var presenter: SearchTeamMvpPresenter<SearchTeamMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var teamAdapter: TeamAdapter

    lateinit var query: String
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        presenter.onAttach(this)
        teamAdapter.setCallback(this)
        query = intent.getStringExtra("query")
        Log.d("Keyword", query)
        init(query)
    }

    private fun init(query: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarTitle.text = "Search Team"

        presenter.searchTeam(query)

        team_list_search.layoutManager = gridLayoutManager
        team_list_search.setHasFixedSize(true)
        team_list_search.adapter = teamAdapter
    }

    override fun updateSearchTeam(listTeam: MutableList<Team>) {
        teamAdapter.clearItem()
        teamAdapter.addItems(listTeam)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuItem = menu

        val searchView = menuItem?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search Teams"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchTeam(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.actionSearch -> {
                val searchView = menuItem?.findItem(R.id.actionSearch)?.actionView as SearchView?

                searchView?.queryHint = "Search Teams"

                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        presenter.searchTeam(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
            }

            R.id.about -> {
                startActivity(AboutActivity.getStartIntent(this))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTeamClick(team: Team) {
        startActivity<DetailTeamActivity>("team_detail" to team)
    }
}
