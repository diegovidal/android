package br.com.vp.advancedandroid.ui

import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 09/05/2018.
 */

@Module
object TestActivityViewInterceptorModule {

    @JvmStatic
    @Provides
    fun provideActivityViewInterceptor(): ActivityViewInterceptor {
        return ActivityViewInterceptor.DEFAULT
    }
}