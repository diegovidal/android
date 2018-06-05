package br.com.vp.findtheday.di

import br.com.vp.findtheday.DayFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author diegovidal on 05/06/2018.
 */

@Singleton
@Component(modules = [
    MyApplicationModule::class
])
interface MyApplicationComponent {

    fun inject(dayFragment: DayFragment)
}