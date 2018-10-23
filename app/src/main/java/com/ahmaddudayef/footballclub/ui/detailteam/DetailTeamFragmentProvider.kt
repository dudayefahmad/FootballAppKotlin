package com.ahmaddudayef.footballclub.ui.detailteam

import com.ahmaddudayef.footballclub.ui.infoteam.InfoTeamFragment
import com.ahmaddudayef.footballclub.ui.infoteam.InfoTeamModule
import com.ahmaddudayef.footballclub.ui.player.PlayerFragment
import com.ahmaddudayef.footballclub.ui.player.PlayerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
@Module
abstract class DetailTeamFragmentProvider {
    @ContributesAndroidInjector(modules = [(InfoTeamModule::class)])
    internal abstract fun provideInfoTeamFragmentFactory(): InfoTeamFragment

    @ContributesAndroidInjector(modules = [(PlayerModule::class)])
    internal abstract fun providePlayerFragmentFactory(): PlayerFragment
}