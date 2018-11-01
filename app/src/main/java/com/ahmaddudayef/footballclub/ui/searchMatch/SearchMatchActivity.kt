package com.ahmaddudayef.footballclub.ui.searchMatch

import android.os.Bundle
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.about.AboutActivity
import com.ahmaddudayef.footballclub.ui.base.BaseActivity
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchAdapter
import com.franmontiel.attributionpresenter.AttributionPresenter
import com.franmontiel.attributionpresenter.entities.Attribution
import com.franmontiel.attributionpresenter.entities.Library
import com.franmontiel.attributionpresenter.entities.License
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SearchMatchActivity : BaseActivity(), SearchMatchMvpView, PrevMatchAdapter.Callback {

    @Inject
    lateinit var presenter: SearchMatchMvpPresenter<SearchMatchMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var prevMatchAdapter: PrevMatchAdapter

    lateinit var query: String
    private var menuItem: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        presenter.onAttach(this)
        prevMatchAdapter.setCallback(this)
        query = intent.getStringExtra("query")
        Log.d("Keyword", query)
        init(query)
    }

    private fun init(query: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbarTitle.text = "Search Match"

        presenter.searchMatch(query)

        match_list_search.layoutManager = linearLayoutManager
        match_list_search.setHasFixedSize(true)
        match_list_search.adapter = prevMatchAdapter
    }

    override fun updateSearchMatch(listMatch: MutableList<EventsItem>) {
        Log.d("Data Search Match", listMatch.toString())
        prevMatchAdapter.clearItems()
        prevMatchAdapter.addItems(listMatch)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuItem = menu

        val searchView = menuItem?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchMatch(query)
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

                searchView?.queryHint = "Search matches"

                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        presenter.searchMatch(query)
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

    override fun onPrevMatchClick(eventsItem: EventsItem) {
        startActivity<DetailMatchActivity>("match_detail" to eventsItem)
    }
}
