package com.ahmaddudayef.footballclub.ui.favoritematch


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.schedule.Events
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : BaseFragment(), FavoriteMatchMvpView {

    @Inject
    lateinit var presenterMatch: FavoriteMatchMvpPresenter<FavoriteMatchMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private var events: MutableList<EventsItem> = mutableListOf()
    private var adapter = FavoriteMatchAdapter(events)

    companion object {
        fun newInstance() = FavoriteMatchFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        presenterMatch.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        listFavMatch.layoutManager = linearLayoutManager
        listFavMatch.adapter = adapter
        swipeRefresh.setOnRefreshListener { getFavoriteFromDb() }
        swipeRefresh.post{
            swipeRefresh.isRefreshing = true
            getFavoriteFromDb()
        }

    }

    private fun getFavoriteFromDb() {
        events.clear()
        presenterMatch.getNextMatch(context)
        swipeRefresh.isRefreshing = false
    }

    override fun showMatchFavorite(matches: Events) {
        swipeRefresh.isRefreshing = false
        events.add(matches.events!![0])
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenterMatch.onDetach()
        super.onDestroy()
    }

}
