package com.ahmaddudayef.footballclub.ui.detailplayer

import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
@Module
class DetailPlayerModule {

    @Provides
    fun provideDetailPlayerMvpPresenter(presenter: DetailPlayerPresenter<DetailPlayerMvpView>): DetailPlayerMvpPresenter<DetailPlayerMvpView>{
        return presenter
    }
}