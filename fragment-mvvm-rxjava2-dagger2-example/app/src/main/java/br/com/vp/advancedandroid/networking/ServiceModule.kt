package br.com.vp.advancedandroid.networking

import br.com.vp.advancedandroid.model.ZonedDateTimeAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author diegovidal on 24/04/2018.
 */

@Module(includes = [
    NetworkModule::class
])
object ServiceModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideMoshi(): Moshi{

        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(ZonedDateTimeAdapter())
                .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, callFactory: Call.Factory, @Named("base_url") baseUrl: String): Retrofit{

        return Retrofit.Builder()
                .callFactory(callFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }

}