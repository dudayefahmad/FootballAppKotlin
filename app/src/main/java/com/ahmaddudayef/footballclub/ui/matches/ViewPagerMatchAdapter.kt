package com.ahmaddudayef.footballclub.ui.matches

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchFragment

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
class ViewPagerMatchAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    var fragmentList = arrayListOf<Fragment>()
    var titleList = arrayListOf<String>()

    fun populateFragment(fragment: Fragment,title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = titleList[position]

}