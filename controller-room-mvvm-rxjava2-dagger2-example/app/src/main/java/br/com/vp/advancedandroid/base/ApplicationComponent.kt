package br.com.vp.advancedandroid.base

import br.com.vp.advancedandroid.data.RepoServiceModule
import br.com.vp.advancedandroid.database.DatabaseModule
import br.com.vp.advancedandroid.home.MainScreenBindingModule
import br.com.vp.advancedandroid.networking.NetworkModule
import br.com.vp.advancedandroid.networking.ServiceModule
import br.com.vp.advancedandroid.ui.NavigationModule
import dagger.BindsInstance
import javax.inject.Singleton

import dagger.Component

/**
 * @author diegovidal on 11/04/2018.
 */

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ActivityBindingModule::class,
    ServiceModule::class,
    RepoServiceModule::class,
    DatabaseModule::class
])
interface ApplicationComponent {

    fun inject(myApplication: MyApplication)
}
