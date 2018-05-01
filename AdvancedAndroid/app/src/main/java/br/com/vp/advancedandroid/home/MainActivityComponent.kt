package br.com.vp.advancedandroid.home

import br.com.vp.advancedandroid.base.ActivityBindingModule
import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.ui.NavigationModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * @author diegovidal on 11/04/2018.
 */

@ActivityScope
@Subcomponent( modules = [
    MainScreenBindingModule::class,
    NavigationModule::class
])
interface MainActivityComponent: AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>(){

        override fun seedInstance(instance: MainActivity?) {

        }
    }
}