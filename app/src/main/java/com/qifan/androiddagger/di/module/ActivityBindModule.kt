package com.qifan.androiddagger.di.module

import com.qifan.androiddagger.MainActivity
import com.qifan.androiddagger.SecondActivity
import com.qifan.androiddagger.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Qifan on 2019-08-01.
 */
@Module
abstract class ActivityBindModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivity.Module::class])
    internal abstract fun mainActivity(): MainActivity


    @ActivityScope
    @ContributesAndroidInjector(modules = [SecondActivity.Module::class])
    internal abstract fun secondActivity(): SecondActivity
}