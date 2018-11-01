package com.ahmaddudayef.footballclub.ui.favorites


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
import com.ahmaddudayef.footballclub.ui.favoritematch.FavoriteMatchFragment
import com.ahmaddudayef.footballclub.ui.favoriteplayer.FavoritePlayerFragment
import com.ahmaddudayef.footballclub.ui.favoriteteam.FavoriteTeamFragment
import com.ahmaddudayef.footballclub.ui.searchTeam.SearchTeamActivity
import com.franmontiel.attributionpresenter.AttributionPresenter
import com.franmontiel.attributionpresenter.entities.Attribution
import com.franmontiel.attributionpresenter.entities.Library
import com.franmontiel.attributionpresenter.entities.License
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment(), FavoriteMvpView {

    @Inject
    lateinit var presenter: FavoriteMvpPresenter<FavoriteMvpView>
    private var menuItem: Menu? = null

    companion object {
        fun newInstance() = FavoriteFragment()
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun setUp(view: View) {
        presenter.onAttach(this)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerFavoriteAdapter(childFragmentManager)
        adapter.populateFragment(FavoriteMatchFragment.newInstance(), "Favorite Match")
        adapter.populateFragment(FavoriteTeamFragment.newInstance(), "Favorite Team")
        adapter.populateFragment(FavoritePlayerFragment.newInstance(), "Favorite Player")
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
        menuItem?.findItem(R.id.actionSearch)?.setVisible(false)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                startActivity(AboutActivity.getStartIntent(context!!))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
