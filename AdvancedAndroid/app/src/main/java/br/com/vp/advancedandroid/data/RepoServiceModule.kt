package br.com.vp.advancedandroid.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
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
}