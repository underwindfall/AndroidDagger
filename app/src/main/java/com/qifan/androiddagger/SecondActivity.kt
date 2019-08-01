package com.qifan.androiddagger

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.qifan.androiddagger.di.scope.ActivityScope
import com.qifan.androiddagger.di.scope.FragmentScope
import dagger.Provides
import dagger.android.*
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
        setContentView(R.layout.activity_fragment)
        replaceFragmentInActivity(TestFragment(), R.id.container)
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


    @dagger.Module
    abstract class FragmentBindingModule {
        @FragmentScope
        @ContributesAndroidInjector(modules = [TestFragment.Module::class])
        internal abstract fun testFragment(): TestFragment
    }
}


fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.beginTransaction()
        .replace(frameId, fragment)
        .commit()
}