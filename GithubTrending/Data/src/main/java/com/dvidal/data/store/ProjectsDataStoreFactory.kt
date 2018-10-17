package com.dvidal.data.store

import com.dvidal.data.repository.ProjectsDataStore
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
open class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {

    open fun getDataStore(projectsCached: Boolean,
                          cacheExpired: Boolean): ProjectsDataStore {

        return if (projectsCached && !cacheExpired) projectsCacheDataStore else projectsRemoteDataStore
    }

    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }
}