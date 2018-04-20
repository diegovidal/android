package br.com.vp.advancedandroid.base

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 11/04/2018.
 */

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context{

        return application
    }
}
