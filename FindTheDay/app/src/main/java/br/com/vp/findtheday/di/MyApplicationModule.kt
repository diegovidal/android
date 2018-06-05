package br.com.vp.findtheday.di

import br.com.vp.findtheday.DayChooser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author diegovidal on 05/06/2018.
 */

@Module
object MyApplicationModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideDayChooser(): DayChooser {
        return DayChooser()
    }
}