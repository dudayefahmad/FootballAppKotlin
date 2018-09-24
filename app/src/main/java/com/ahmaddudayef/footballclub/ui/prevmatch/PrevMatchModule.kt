package com.ahmaddudayef.footballclub.ui.prevmatch

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
@Module
class PrevMatchModule {

    @Provides
    fun providePrevMatchMvpPresenter(presenter: PrevPresenter<PrevMvpView>): PrevMvpPresenter<PrevMvpView>{
        return presenter
    }

    @Provides
    fun provideLinearLayourManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideLastMatchAdapter(): PrevMatchAdapter {
        return PrevMatchAdapter(ArrayList())
    }
}