package br.com.vp.advancedandroid.ui

import dagger.Provides

/**
 * @author diegovidal on 09/05/2018.
 */
object AcitivtyViewInterceptorModule {

    @JvmStatic
    @Provides
    fun provideActivityViewInterceptor(): ActivityViewInterceptor {
        return ActivityViewInterceptor.DEFAULT
    }
}