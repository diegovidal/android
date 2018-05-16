package br.com.vp.advancedandroid.base

import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.home.MainActivity
import br.com.vp.advancedandroid.home.TestScreenBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author diegovidal on 30/04/2018.
 */

@Module
abstract class TestActivityBindingModule {

    @ContributesAndroidInjector(modules = [
        TestScreenBindingModule::class
    ])
    @ActivityScope
    abstract fun provideMainActivityInjector(): MainActivity
}