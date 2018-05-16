package br.com.vp.advancedandroid.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author diegovidal on 30/04/2018.
 */

@Module
abstract class TestRepoServiceModule {

    @Binds
    abstract fun bindRepoService(testRepoService: TestRepoService): RepoService

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Named("network_scheduler")
        fun provideNetworkScheduler(retrofit: Retrofit): Scheduler {
            return Schedulers.trampoline()
        }
    }
}