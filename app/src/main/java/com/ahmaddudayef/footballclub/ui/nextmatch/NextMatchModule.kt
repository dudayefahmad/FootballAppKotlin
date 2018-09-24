package com.ahmaddudayef.footballclub.ui.nextmatch

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
@Module
class NextMatchModule {

    @Provides
    fun provideNextMatchMvpPresenter(presenter: NextPresenter<NextMvpView>): NextMvpPresenter<NextMvpView>{
        return presenter
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideNextMatchAdapter(): NextMatchAdapter {
        return NextMatchAdapter(ArrayList())
    }
}