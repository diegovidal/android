package br.com.vp.advancedandroid.ui

import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.ActivityLifecycleTask
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Singleton

/**
 * @author diegovidal on 23/04/2018.
 */

@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindScreenNavigator(defaultScreenNavigator: DefaultScreenNavigator): ScreenNavigator

    @Binds
    @IntoSet
    abstract fun bindScreenNavigatorTask(screenNavigator: DefaultScreenNavigator): ActivityLifecycleTask
}