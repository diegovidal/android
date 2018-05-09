package br.com.vp.advancedandroid.ui

import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.di.ScreenScope
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * @author diegovidal on 23/04/2018.
 */

@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindScreenNavigator(defaultScreenNavigator: DefaultScreenNavigator): ScreenNavigator
}