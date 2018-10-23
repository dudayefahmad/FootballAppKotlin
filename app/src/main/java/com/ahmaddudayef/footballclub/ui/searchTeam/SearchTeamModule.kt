package com.ahmaddudayef.footballclub.ui.searchTeam

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import com.ahmaddudayef.footballclub.ui.teams.TeamAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/22/2018.
 */
@Module
class SearchTeamModule {

    @Provides
    fun provideSearchTeamMvpPresenter(presenter: SearchTeamPresenter<SearchTeamMvpView>): SearchTeamMvpPresenter<SearchTeamMvpView> {
        return presenter
    }

    @Provides
    fun provideGridLayoutManager(@ApplicationContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
    }


    @Provides
    fun provideTeamAdapter(): TeamAdapter {
        return TeamAdapter(ArrayList())
    }
}