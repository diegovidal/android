package br.com.vp.advancedandroid.base

import javax.inject.Singleton

import dagger.Component

/**
 * @author diegovidal on 11/04/2018.
 */

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ActivityBindingModule::class
])
interface ApplicationComponent {

    fun inject(myApplication: MyApplication)
}
