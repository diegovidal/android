package br.com.vp.advancedandroid.base

import android.app.Application
import br.com.vp.advancedandroid.BuildConfig
import br.com.vp.advancedandroid.di.ActivityInjector
import br.com.vp.advancedandroid.di.ScreenInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * @author diegovidal on 11/04/2018.
 */

open class MyApplication : Application() {

    @Inject
    lateinit var activityInjector: ActivityInjector

    protected lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = initComponent()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    open fun initComponent(): ApplicationComponent {

        return DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
