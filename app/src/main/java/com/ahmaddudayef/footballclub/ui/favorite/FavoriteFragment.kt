package com.ahmaddudayef.footballclub.ui.favorite


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
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment(), FavoriteMvpView {

    @Inject
    lateinit var presenter: FavoriteMvpPresenter<FavoriteMvpView>
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private var events: MutableList<EventsItem> = mutableListOf()
    private var adapter = FavoriteAdapter(events)

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        presenter.onAttach(this)
        return view
    }

    override fun setUp(view: View) {
        listFavMatch.layoutManager = LinearLayoutManager(context)
        listFavMatch.adapter = adapter
        swipeRefresh.setOnRefreshListener { getFavoriteFromDb() }
        swipeRefresh.post{
            swipeRefresh.isRefreshing = true
            getFavoriteFromDb()
        }

    }

    private fun getFavoriteFromDb() {
        events.clear()
        presenter.getNextMatch(context)
        swipeRefresh.isRefreshing = false
    }

    override fun showMatchFavorite(matches: Events) {
        swipeRefresh.isRefreshing = false
        events.add(matches.events!![0])
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
