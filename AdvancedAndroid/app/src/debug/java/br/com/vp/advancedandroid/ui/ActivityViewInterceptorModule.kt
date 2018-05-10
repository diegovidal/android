package br.com.vp.advancedandroid.ui

import dagger.Binds
import dagger.Module

/**
 * @author diegovidal on 09/05/2018.
 */

@Module
abstract class ActivityViewInterceptorModule {

    @Binds
    abstract fun bindDebugActivityViewInterceptor(activityViewInterceptor: DebugActivityViewInterceptor)
            : ActivityViewInterceptor
}