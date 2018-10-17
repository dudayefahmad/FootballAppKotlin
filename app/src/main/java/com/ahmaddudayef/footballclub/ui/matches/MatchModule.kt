package com.ahmaddudayef.footballclub.ui.matches

import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
@Module
class MatchModule {
    @Provides
    fun provideMatchMvpPresenter(presenter: MatchPresenter<MatchMvpView>): MatchMvpPresenter<MatchMvpView>{
        return presenter
    }
}