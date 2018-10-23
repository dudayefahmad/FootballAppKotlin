package com.ahmaddudayef.footballclub.ui.nextmatch

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.league.Leagues
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.startActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class NextMatchFragment: BaseFragment(), NextMvpView, NextMatchAdapter.Callback {

    @Inject
    lateinit var presenter: NextMvpPresenter<NextMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var nextMatchAdapter: NextMatchAdapter

    companion object {
        fun newInstance() = NextMatchFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_next_match, container, false)
        presenter.onAttach(this)
        nextMatchAdapter.setCallback(this)
        return view
    }

    override fun setUp(view: View) {
        presenter.getAllLeagues()
        presenter.getNextScheduleList("4328")

        match_list.layoutManager = linearLayoutManager
        match_list.setHasFixedSize(true)
        match_list.adapter = nextMatchAdapter
    }

    override fun updateLeagueid(leagues: Leagues) {
        val strLeagues = leagues.leagues.map { it.strLeague }
        val leaguesId = leagues.leagues.map { it.idLeague }
        setDropdownLeague(spinnerNextMatch, strLeagues, object : SpinnerListener {
            override fun getLeague(position: Int) {
                nextMatchAdapter.clearItems()
                presenter.getNextScheduleList(leaguesId[position])
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
        Log.d("Data Match", listTeam.toString())
        match_list.visibility = VISIBLE

        nextMatchAdapter.addItems(listTeam)
    }

    override fun onNextMatchClick(eventsItem: EventsItem) {
        startActivity<DetailMatchActivity>("match_detail" to eventsItem)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}