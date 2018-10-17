package com.dvidal.ui.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dvidal.presentation.BrowseBookmarkedProjectsViewModel
import com.dvidal.presentation.BrowseProjectsViewModel
import com.dvidal.ui.di.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(viewModel: BrowseProjectsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseBookmarkedProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(
            viewModel: BrowseBookmarkedProjectsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)