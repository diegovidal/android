package br.com.vp.advancedandroid.ui

import br.com.vp.advancedandroid.lifecycle.ActivityLifecycleTask
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

/**
 * @author diegovidal on 08/05/2018.
 */

@Module
abstract class TestNavigationModule {

    @Binds
    abstract fun bindScreenNavigator(screenNavigator: TestScreenNavigator): ScreenNavigator

    @Binds
    @IntoSet
    abstract fun bindScreenNavigatorTask(screenNavigator: TestScreenNavigator): ActivityLifecycleTask
}