package com.ahmaddudayef.footballclub.ui.matches


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.SearchView
import android.view.*

import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.ui.about.AboutActivity
import com.ahmaddudayef.footballclub.ui.base.BaseFragment
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchFragment
import com.ahmaddudayef.footballclub.ui.searchMatch.SearchMatchActivity
import com.franmontiel.attributionpresenter.AttributionPresenter
import com.franmontiel.attributionpresenter.entities.Attribution
import com.franmontiel.attributionpresenter.entities.Library
import com.franmontiel.attributionpresenter.entities.License
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : BaseFragment(), MatchMvpView {

    @Inject
    lateinit var presenter: MatchMvpPresenter<MatchMvpView>

    private var menuItem: Menu? = null

    companion object {
        fun newInstance() = MatchFragment()
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        menuItem = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSearch -> {
                val searchView = menuItem?.findItem(R.id.actionSearch)?.actionView as SearchView?

                searchView?.queryHint = "Search Matches"

                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val intent = Intent(context, SearchMatchActivity::class.java)
                        intent.putExtra("query", query)
                        startActivity(intent)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
            }

            R.id.about -> {
                startActivity(AboutActivity.getStartIntent(context!!))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
