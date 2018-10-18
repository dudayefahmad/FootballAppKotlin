package com.ahmaddudayef.footballclub.ui.detailteam

import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/18/2018.
 */
@Module
class DetailTeamModule {
    @Provides
    fun provideDetailTeamPresenter(presenter: DetailTeamPresenter<DetailTeamMvpView>): DetailTeamMvpPresenter<DetailTeamMvpView>{
        return presenter
    }
}