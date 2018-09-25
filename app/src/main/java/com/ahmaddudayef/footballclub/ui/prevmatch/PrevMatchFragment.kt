package com.ahmaddudayef.footballclub.ui.prevmatch

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_prev_match.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class PrevMatchFragment : BaseFragment(), PrevMvpView, PrevMatchAdapter.Callback {


    @Inject
    lateinit var presenter: PrevMvpPresenter<PrevMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var prevMatchAdapter: PrevMatchAdapter

    companion object {
        fun newInstance() = PrevMatchFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_prev_match, container, false)
        presenter.onAttach(this)
        prevMatchAdapter.setCallback(this)
        return view
    }

    override fun setUp(view: View) {
        presenter.getAllLeagues()
        presenter.getPrevScheduleList("4328")

        match_list.layoutManager = linearLayoutManager
        match_list.setHasFixedSize(true)
        match_list.adapter = prevMatchAdapter
    }

    override fun updateLeagueid(leagues: Leagues) {
        val strLeagues = leagues.leagues.map { it.strLeague }
        val leaguesId = leagues.leagues.map { it.idLeague }
        setDropdownLeague(spinnerPrevMatch, strLeagues, object : PrevMatchFragment.SpinnerListener {
            override fun getLeague(position: Int) {
                prevMatchAdapter.clearItems()
                presenter.getPrevScheduleList(leaguesId[position])
            }
        })
    }

    private fun setDropdownLeague(spinner: Spinner, item: List<String>, listener: SpinnerListener) {
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

    override fun updateList(listTeam: MutableList<EventsItem>) {
        match_list.visibility = VISIBLE
        prevMatchAdapter.addItems(listTeam)
    }

    override fun onMatchClick(eventsItem: EventsItem) {
        startActivity<DetailMatchActivity>("match_detail" to eventsItem)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}