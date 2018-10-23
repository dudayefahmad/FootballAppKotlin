package com.ahmaddudayef.footballclub.ui.home

import com.ahmaddudayef.footballclub.ui.favoritematch.FavoriteMatchFragment
import com.ahmaddudayef.footballclub.ui.favoritematch.FavoriteMatchModule
import com.ahmaddudayef.footballclub.ui.favoriteplayer.FavoritePlayerFragment
import com.ahmaddudayef.footballclub.ui.favoriteplayer.FavoritePlayerModule
import com.ahmaddudayef.footballclub.ui.favorites.FavoriteFragment
import com.ahmaddudayef.footballclub.ui.favorites.FavoriteModule
import com.ahmaddudayef.footballclub.ui.favoriteteam.FavoriteTeamFragment
import com.ahmaddudayef.footballclub.ui.favoriteteam.FavoriteTeamModule
import com.ahmaddudayef.footballclub.ui.matches.MatchFragment
import com.ahmaddudayef.footballclub.ui.matches.MatchModule
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchFragment
import com.ahmaddudayef.footballclub.ui.nextmatch.NextMatchModule
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchFragment
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchModule
import com.ahmaddudayef.footballclub.ui.teams.TeamFragment
import com.ahmaddudayef.footballclub.ui.teams.TeamModule
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

    @ContributesAndroidInjector(modules = [(FavoriteMatchModule::class)])
    internal abstract fun provideFavoritMatchFragmentFactory(): FavoriteMatchFragment

    @ContributesAndroidInjector(modules = [(FavoriteTeamModule::class)])
    internal abstract fun provideFavoriteTeamFragmentFactory(): FavoriteTeamFragment

    @ContributesAndroidInjector(modules = [(MatchModule::class)])
    internal abstract fun provideMatchesFragmentFactory(): MatchFragment

    @ContributesAndroidInjector(modules = [(TeamModule::class)])
    internal abstract fun provideTeamFragmentFactory(): TeamFragment

    @ContributesAndroidInjector(modules = [(FavoriteModule::class)])
    internal abstract fun provideFavoriteFragmentFactory(): FavoriteFragment

    @ContributesAndroidInjector(modules = [(FavoritePlayerModule::class)])
    internal abstract fun provideFavoritePlayerFragmentFactory(): FavoritePlayerFragment
}