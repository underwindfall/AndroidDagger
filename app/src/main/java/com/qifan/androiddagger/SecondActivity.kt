package com.qifan.androiddagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qifan.androiddagger.di.scope.ActivityScope
import dagger.Provides
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by Qifan on 2019-08-01.
 */
class SecondActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var className: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = className
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @dagger.Module
    class Module {
        @Provides
        @ActivityScope
        fun provideClassName(): String {
            return SecondActivity::class.java.simpleName
        }
    }
}