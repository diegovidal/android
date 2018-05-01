package br.com.vp.advancedandroid.ui

import dagger.Binds
import dagger.Module

/**
 * @author diegovidal on 23/04/2018.
 */

@Module
abstract class NavigationModule {

    @Binds
    abstract fun provideScreenNavigator(defaultScreenNavigator: DefaultScreenNavigator): ScreenNavigator
}