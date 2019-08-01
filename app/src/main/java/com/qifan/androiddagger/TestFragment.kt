package com.qifan.androiddagger

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qifan.androiddagger.di.scope.FragmentScope
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.test_fragment.*
import javax.inject.Inject

/**
 * Created by Qifan on 2019-08-01.
 */
class TestFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var testName: String


    @Inject
    lateinit var testFragment: Test

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "$testName fragmentObject:${testFragment.string}"
    }


    @dagger.Module
    class Module {
        @Provides
        @FragmentScope
        fun provideFragmentTest(): Test = Test("testFragment")
    }
}


data class Test(
    val string: String
)