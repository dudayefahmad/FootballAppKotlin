package com.ahmaddudayef.footballclub.ui.home

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
}