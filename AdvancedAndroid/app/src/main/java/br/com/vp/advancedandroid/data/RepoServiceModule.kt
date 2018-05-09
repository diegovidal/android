package br.com.vp.advancedandroid.data

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author diegovidal on 24/04/2018.
 */

@Module
object RepoServiceModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideRepoService(retrofit: Retrofit): RepoService {

        return retrofit.create(RepoService::class.java)
    }

    @JvmStatic
    @Provides
    @Named("network_scheduler")
    fun provideNetworkScheduler(retrofit: Retrofit): Scheduler {

        return Schedulers.io()
    }
}