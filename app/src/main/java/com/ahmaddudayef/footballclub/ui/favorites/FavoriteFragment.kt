package com.ahmaddudayef.footballclub.ui.favorites


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
import com.ahmaddudayef.footballclub.ui.favoritematch.FavoriteMatchFragment
import com.ahmaddudayef.footballclub.ui.favoriteplayer.FavoritePlayerFragment
import com.ahmaddudayef.footballclub.ui.favoriteteam.FavoriteTeamFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment(), FavoriteMvpView {

    @Inject
    lateinit var presenter: FavoriteMvpPresenter<FavoriteMvpView>

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

}
