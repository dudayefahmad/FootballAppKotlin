package com.ahmaddudayef.footballclub.ui.infoteam

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/19/2018.
 */
@Module
class InfoTeamModule{
    @Provides
    fun provideInfoTeamMvpPresenter(presenter: InfoTeamPresenter<InfoTeamMvpView>): InfoTeamMvpPresenter<InfoTeamMvpView>{
        return presenter
    }
}