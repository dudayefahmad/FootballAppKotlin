package com.ahmaddudayef.footballclub

import android.app.Activity
import android.app.Application
import com.ahmaddudayef.footballclub.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Ahmad Dudayef on 9/17/2018.
 */
class FootballApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

}