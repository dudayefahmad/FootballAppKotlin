package com.ahmaddudayef.footballclub.ui.prevmatch

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.data.network.model.schedule.EventsItem
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
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
        match_list.layoutManager = linearLayoutManager
        match_list.setHasFixedSize(true)
        match_list.adapter = prevMatchAdapter

        presenter.getPrevScheduleList()
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