package com.ahmaddudayef.footballclub.di.module

import com.ahmaddudayef.footballclub.ui.detail.DetailMatchActivity
import com.ahmaddudayef.footballclub.ui.detail.DetailMatchModule
import com.ahmaddudayef.footballclub.ui.detailplayer.DetailPlayerActivity
import com.ahmaddudayef.footballclub.ui.detailplayer.DetailPlayerModule
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamActivity
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamFragmentProvider
import com.ahmaddudayef.footballclub.ui.detailteam.DetailTeamModule
import com.ahmaddudayef.footballclub.ui.home.FragmentProvider
import com.ahmaddudayef.footballclub.ui.home.HomeActivity
import com.ahmaddudayef.footballclub.ui.home.HomeActivityModule
import com.ahmaddudayef.footballclub.ui.player.PlayerModule
import com.ahmaddudayef.footballclub.ui.searchMatch.SearchMatchActivity
import com.ahmaddudayef.footballclub.ui.searchMatch.SearchMatchModule
import com.ahmaddudayef.footballclub.ui.searchTeam.SearchTeamActivity
import com.ahmaddudayef.footballclub.ui.searchTeam.SearchTeamModule
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

    @ContributesAndroidInjector(modules = [(DetailTeamModule::class), (DetailTeamFragmentProvider::class)])
    abstract fun bindDetailTeamActivity(): DetailTeamActivity

    @ContributesAndroidInjector(modules = [(SearchMatchModule::class)])
    abstract fun bindSearchMatchActivity(): SearchMatchActivity

    @ContributesAndroidInjector(modules = [(SearchTeamModule::class)])
    abstract fun bindSearchTeamActivity(): SearchTeamActivity

    @ContributesAndroidInjector(modules = [(DetailPlayerModule::class)])
    abstract fun bindDetailPlayerActivity(): DetailPlayerActivity
}