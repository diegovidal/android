package br.com.vp.advancedandroid.base

import android.app.Application
import br.com.vp.advancedandroid.di.ActivityInjector
import br.com.vp.advancedandroid.di.ScreenInjector
import javax.inject.Inject

/**
 * @author diegovidal on 11/04/2018.
 */
class MyApplication : Application() {


    var activityInjector: ActivityInjector? = null
        @Inject set

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)
    }
}
