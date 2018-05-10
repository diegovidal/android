package br.com.vp.advancedandroid.networking

import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author diegovidal on 24/04/2018.
 */

@Module
object NetworkModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttp(mockInterceptor: MockInterceptor): Call.Factory{

        return OkHttpClient.Builder()
                .addInterceptor(mockInterceptor)
                .build()
    }

    @JvmStatic
    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String{
        return "https://api.github.com/"
    }
}