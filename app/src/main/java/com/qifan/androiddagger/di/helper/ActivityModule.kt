package com.qifan.androiddagger.di.helper

import android.app.Activity
import androidx.core.app.ComponentActivity
import com.qifan.androiddagger.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

/**
 * Created by Qifan on 2019-08-01.
 */
@Module
interface ActivityModule<T : ComponentActivity> {

    @Binds
    @ActivityScope
    fun provideActivityName(activity: T): Activity
}