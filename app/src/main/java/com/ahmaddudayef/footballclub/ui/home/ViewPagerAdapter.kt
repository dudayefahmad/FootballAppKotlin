package com.ahmaddudayef.footballclub.ui.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ahmaddudayef.footballclub.ui.favorites.FavoriteFragment
import com.ahmaddudayef.footballclub.ui.matches.MatchFragment
import com.ahmaddudayef.footballclub.ui.teams.TeamFragment

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        return when (position){
            0 -> MatchFragment.newInstance()
            1 -> TeamFragment.newInstance()
            2 -> FavoriteFragment.newInstance()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }

    override fun getCount(): Int {
        return 3
    }

}