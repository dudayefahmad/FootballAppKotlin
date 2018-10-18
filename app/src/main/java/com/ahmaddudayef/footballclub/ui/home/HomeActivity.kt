package com.ahmaddudayef.footballclub.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.ui.base.BaseActivity
import com.ahmaddudayef.footballclub.utils.BottomNavigationViewHelper
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.matchParent
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class HomeActivity: BaseActivity(), HomeMvpView, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var presenter: HomeMvpPresenter<HomeMvpView>
    @Inject
    lateinit var viewPagerAdapter: ViewPagerAdapter

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onAttach(this)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewPager.offscreenPageLimit = 3
        viewPager.adapter = viewPagerAdapter

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.matches -> {
                    viewPager.setCurrentItem(0, true)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.teams -> {
                    viewPager.setCurrentItem(1, true)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.favorite_match -> {
                    viewPager.setCurrentItem(2, true)
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.selectedItemId = R.id.matches
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}