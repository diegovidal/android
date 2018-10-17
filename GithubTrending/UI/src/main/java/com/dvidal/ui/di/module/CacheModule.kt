package com.dvidal.ui.di.module

import android.app.Application
import com.dvidal.cache.ProjectsCacheImpl
import com.dvidal.cache.db.ProjectsDatabase
import com.dvidal.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}