package com.ahmaddudayef.footballclub.di.component

import com.ahmaddudayef.footballclub.FootballApplication
import com.ahmaddudayef.footballclub.di.module.ActivityBindingModule
import com.ahmaddudayef.footballclub.di.module.ApplicationModule
import com.ahmaddudayef.footballclub.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
@Singleton
@Component(
        modules =
        [
            AndroidSupportInjectionModule::class,
            NetworkModule::class,
            ApplicationModule::class,
            ActivityBindingModule::class
        ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(footballApplication: FootballApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(footballApplication: FootballApplication)
}