package com.ahmaddudayef.footballclub.ui.favoriteplayer

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import com.ahmaddudayef.footballclub.ui.favorites.FavoritePresenter
import com.ahmaddudayef.footballclub.ui.player.PlayerPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
@Module
class FavoritePlayerModule {

    @Provides
    fun provideFavoritePlayerMvpPresenter(presenter: FavoritePlayerPresenter<FavoritePlayerMvpView>): FavoritePlayerMvpPresenter<FavoritePlayerMvpView> {
        return presenter
    }

    @Provides
    fun provideGridLayoutManager(@ApplicationContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
    }
}