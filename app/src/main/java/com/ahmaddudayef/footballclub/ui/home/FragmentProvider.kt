package com.ahmaddudayef.footballclub.ui.home

import com.ahmaddudayef.footballclub.ui.favorite.FavoriteFragment
import com.ahmaddudayef.footballclub.ui.favorite.FavoriteModule
import com.ahmaddudayef.footballclub.ui.matches.MatchFragment
import com.ahmaddudayef.footballclub.ui.matches.MatchModule
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchModule
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchFragment
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
@Module
abstract class FragmentProvider {

    @ContributesAndroidInjector(modules = [(NextMatchModule::class)])
    internal abstract fun provideNextMatchFragmentFactory(): NextMatchFragment

    @ContributesAndroidInjector(modules = [(PrevMatchModule::class)])
    internal abstract fun providePrevMatchFragmentFactory(): PrevMatchFragment

    @ContributesAndroidInjector(modules = [(FavoriteModule::class)])
    internal abstract fun provideFavoriteFragmentFactory(): FavoriteFragment

    @ContributesAndroidInjector(modules = [(MatchModule::class)])
    internal abstract fun provideMatchesFragmentFactory(): MatchFragment
}