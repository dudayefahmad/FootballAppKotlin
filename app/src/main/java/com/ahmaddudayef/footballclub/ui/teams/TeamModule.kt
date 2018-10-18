package com.ahmaddudayef.footballclub.ui.teams

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.flux.ui.home.tv.ItemOffsetDecoration
import com.ahmaddudayef.footballclub.R
import com.ahmaddudayef.footballclub.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/17/2018.
 */
@Module
class TeamModule {

    @Provides
    fun provideTeamMvpPresenter(presenter: TeamPresenter<TeamMvpView>): TeamMvpPresenter<TeamMvpView>{
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