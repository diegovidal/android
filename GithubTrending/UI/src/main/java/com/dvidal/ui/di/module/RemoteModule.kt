package com.dvidal.ui.di.module

import com.dvidal.data.repository.ProjectsRemote
import com.dvidal.remote.ProjectsRemoteImpl
import com.dvidal.remote.service.GithubTrendingService
import com.dvidal.remote.service.GithubTrendingServiceFactory
import com.dvidal.ui.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */
@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}