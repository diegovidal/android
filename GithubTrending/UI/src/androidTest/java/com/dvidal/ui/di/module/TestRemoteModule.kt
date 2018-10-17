package com.dvidal.ui.di.module

import android.app.Application
import com.dvidal.cache.db.ProjectsDatabase
import com.dvidal.data.repository.ProjectsCache
import com.dvidal.data.repository.ProjectsRemote
import com.dvidal.remote.service.GithubTrendingService
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService{
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideProjectsRemote(): ProjectsRemote {
        return mock()
    }
}