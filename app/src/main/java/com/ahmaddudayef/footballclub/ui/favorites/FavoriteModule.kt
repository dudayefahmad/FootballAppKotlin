package com.ahmaddudayef.footballclub.ui.favorites

import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
@Module
class FavoriteModule {
    @Provides
    fun provideFavoriteMvpPresenter(presenter: FavoritePresenter<FavoriteMvpView>): FavoriteMvpPresenter<FavoriteMvpView>{
        return presenter
    }
}