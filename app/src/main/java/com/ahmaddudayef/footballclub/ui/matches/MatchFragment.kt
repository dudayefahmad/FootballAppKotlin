package com.ahmaddudayef.footballclub.ui.matches


import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMvpView
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : BaseFragment(), MatchMvpView {

    @Inject
    lateinit var presenter: MatchMvpPresenter<MatchMvpView>

    companion object {
        fun newInstance() = MatchFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun setUp(view: View) {
        presenter.onAttach(this)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerMatchAdapter(childFragmentManager)
        adapter.populateFragment(NextMatchFragment(), "Next Match")
        adapter.populateFragment(PrevMatchFragment(), "Last Match")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
