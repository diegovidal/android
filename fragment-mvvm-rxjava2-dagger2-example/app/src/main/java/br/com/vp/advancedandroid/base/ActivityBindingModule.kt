package br.com.vp.advancedandroid.base

import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.home.MainActivity
import br.com.vp.advancedandroid.home.MainScreenBindingModule
import br.com.vp.advancedandroid.ui.ActivityViewInterceptorModule
import br.com.vp.advancedandroid.ui.NavigationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author diegovidal on 11/04/2018.
 */

@Module
abstract class ActivityBindingModule {

    /**
     * Using ContributesAndroidInjector removes our ability to prevent the Activity from being injectable.
     *
     * If you inject MainActivity anywhere, it will leak!
     *
     * I suggest sticking with creating the Subcomponent classes yourself, but this is shown here to demonstrate a
     * Dagger's ContributesAndroidInjector feature.
     */
    @ContributesAndroidInjector(modules = [
        MainScreenBindingModule::class,
        NavigationModule::class,
        ActivityViewInterceptorModule::class
    ])
    @ActivityScope
    abstract fun provideMainActivityInjector(): MainActivity
}