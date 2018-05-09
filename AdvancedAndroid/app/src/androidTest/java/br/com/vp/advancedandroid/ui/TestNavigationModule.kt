package br.com.vp.advancedandroid.ui

import dagger.Binds
import dagger.Module

/**
 * @author diegovidal on 08/05/2018.
 */

@Module
abstract class TestNavigationModule {

    @Binds
    abstract fun bindScreenNavigator(screenNavigator: TestScreenNavigator): ScreenNavigator
}