package com.ahmaddudayef.footballclub.ui.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
class ViewPagerFavoriteAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager){
    var fragmentList = arrayListOf<Fragment>()
    var titleList = arrayListOf<String>()

    fun populateFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = titleList[position]
}