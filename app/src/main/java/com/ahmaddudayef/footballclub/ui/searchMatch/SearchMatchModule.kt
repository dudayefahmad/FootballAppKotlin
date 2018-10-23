package com.ahmaddudayef.footballclub.ui.searchMatch

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.footballclub.di.ApplicationContext
import com.ahmaddudayef.footballclub.ui.prevmatch.PrevMatchAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 10/21/2018.
 */
@Module
class SearchMatchModule {

    @Provides
    fun provideSearchMatchMvpPresenter(presenter: SearchMatchPresenter<SearchMatchMvpView>): SearchMatchMvpPresenter<SearchMatchMvpView>{
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