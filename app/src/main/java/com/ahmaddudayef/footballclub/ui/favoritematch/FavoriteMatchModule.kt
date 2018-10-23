package com.ahmaddudayef.footballclub.ui.favoritematch

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 9/27/2018.
 */
@Module
class FavoriteMatchModule {

    @Provides
    fun provideFavoriteMatchMvpPresenter(presenter: FavoriteMatchPresenter<FavoriteMatchMvpView>): FavoriteMatchMvpPresenter<FavoriteMatchMvpView>{
        return presenter
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}