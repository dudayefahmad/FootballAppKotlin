package com.ahmaddudayef.footballclub.ui.favorite

import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
@Module
class FavoriteModule {

    @Provides
    fun provideFavoriteMvpPresenter(presenter: FavoritePresenter<FavoriteMvpView>): FavoriteMvpPresenter<FavoriteMvpView>{
        return presenter
    }
}