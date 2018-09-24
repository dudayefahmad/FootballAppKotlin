package com.ahmaddudayef.footballclub.ui.detail

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 9/21/2018.
 */
@Module
class DetailMatchModule {

    @Provides
    fun provideDetailMatchMvpPresenter(presenter: DetailMatchPresenter<DetailMatchMvpView>): DetailMatchMvpPresenter<DetailMatchMvpView>{
        return presenter
    }
}