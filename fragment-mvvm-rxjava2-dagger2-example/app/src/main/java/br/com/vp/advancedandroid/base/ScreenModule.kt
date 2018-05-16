package br.com.vp.advancedandroid.base

import br.com.vp.advancedandroid.di.ForScreen
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.DisposableManager
import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author diegovidal on 10/05/2018.
 */

@Module
abstract class ScreenModule {

    @Multibinds
    abstract fun screenLifecycleTask(): Set<ScreenLifecycleTask>

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ScreenScope
        @ForScreen
        fun provideDisposableManager(): DisposableManager {
            return DisposableManager()
        }
    }
}