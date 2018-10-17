package com.dvidal.data

import com.dvidal.data.mapper.ProjectMapper
import com.dvidal.data.repository.ProjectsCache
import com.dvidal.data.store.ProjectsDataStoreFactory
import com.dvidal.domain.model.Project
import com.dvidal.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory
): ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {

        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getProjects()
                            .distinctUntilChanged()
                }
                .flatMap {projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }
                .map { it ->
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
                .map {
                    it.map { mapper.mapFromEntity(it) }
                }
    }
}