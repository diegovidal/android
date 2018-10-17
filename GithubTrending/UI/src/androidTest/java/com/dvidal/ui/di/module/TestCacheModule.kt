package com.dvidal.ui.di.module

import android.app.Application
import com.dvidal.cache.db.ProjectsDatabase
import com.dvidal.data.repository.ProjectsCache
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): ProjectsDatabase {
        return ProjectsDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideProjectsCache(): ProjectsCache {
        return mock()
    }
}