package com.ahmaddudayef.footballclub.ui.matches


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMvpView
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchFragment


/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment(), MatchMvpView {

    companion object {
        fun newInstance() = MatchFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerMatchAdapter(childFragmentManager)
        adapter.populateFragment(NextMatchFragment(), "Next Match")
        adapter.populateFragment(PrevMatchFragment(), "Last Match")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {

    }

    override fun showError(message: String) {

    }

}
