package br.com.vp.advancedandroid.base

import android.app.Activity
import br.com.vp.advancedandroid.home.MainActivity
import br.com.vp.advancedandroid.home.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author diegovidal on 11/04/2018.
 */

@Module(subcomponents = [
    MainActivityComponent::class
])
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun provideMainActivityInjector(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}