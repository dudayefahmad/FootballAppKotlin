package com.ahmaddudayef.footballclub.di.module

import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchModule
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamActivity
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamModule
import com.ahmaddudayef.footballclub.ui.home.FragmentProvider
import com.ahmaddudayef.footballclub.ui.home.HomeActivity
import com.ahmaddudayef.footballclub.ui.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(HomeActivityModule::class), (FragmentProvider::class)])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [(DetailMatchModule::class)])
    abstract fun bindDetailMatchActivity(): DetailMatchActivity

    @ContributesAndroidInjector(modules = [(DetailTeamModule::class)])
    abstract fun bindDetailTeamActivity(): DetailTeamActivity
}