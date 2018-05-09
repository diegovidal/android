package br.com.vp.advancedandroid.home

import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.ui.NavigationModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * @author diegovidal on 30/04/2018.
 */

@ActivityScope
@Subcomponent(modules = [
    TestScreenBindingModule::class,
    NavigationModule::class
])
interface TestMainActivityComponent: AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>(){

        override fun seedInstance(instance: MainActivity?) {

        }
    }
}