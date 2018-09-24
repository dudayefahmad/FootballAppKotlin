package com.ahmaddudayef.footballclub.ui.home

import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
@Module
class HomeActivityModule {

    @Provides
    fun provideHomeMvpPresenter(presenter: HomePresenter<HomeMvpView>): HomeMvpPresenter<HomeMvpView>{
        return presenter
    }

    @Provides
    fun provideViewPagerAdapter(homeActivity: HomeActivity): ViewPagerAdapter {
        return ViewPagerAdapter(homeActivity.supportFragmentManager)
    }
}