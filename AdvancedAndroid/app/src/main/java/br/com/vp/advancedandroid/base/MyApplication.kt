package br.com.vp.advancedandroid.base

import android.app.Application
import br.com.vp.advancedandroid.di.ActivityInjector
import javax.inject.Inject

/**
 * @author diegovidal on 11/04/2018.
 */
class MyApplication : Application() {

    @Inject
    lateinit var activityInjector: ActivityInjector

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)
    }
}
