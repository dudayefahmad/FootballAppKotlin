package com.ahmaddudayef.footballclub.ui.favoriteteam

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/23/2018.
 */
@Module
class FavoriteTeamModule {

    @Provides
    fun provideFavoriteTeamMvpPresenter(presenter: FavoriteTeamPresenter<FavoriteTeamMvpView>): FavoriteTeamMvpPresenter<FavoriteTeamMvpView> {
        return presenter
    }

    @Provides
    fun provideGridLayoutManager(@ApplicationContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
    }
}