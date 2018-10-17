package com.dvidal.cache

import com.dvidal.cache.db.ProjectsDatabase
import com.dvidal.cache.mapper.CachedProjectMapper
import com.dvidal.cache.model.Config
import com.dvidal.data.model.ProjectEntity
import com.dvidal.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toSingle
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class ProjectsCacheImpl @Inject constructor(
        private val projectsDatabase: ProjectsDatabase,
        private val mapper: CachedProjectMapper)
    : ProjectsCache {

    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertProjects(
                    projects.map { mapper.mapToCached(it) }
            )
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects()
                .toObservable()
                .map { cachedProjects ->
                    cachedProjects.map { mapper.mapFromCached(it) }
                }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                .toObservable()
                .map { cachedProjects ->
                    cachedProjects.map { mapper.mapFromCached(it) }
                }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
                    Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao().getProjects().isEmpty
                .map {
                    !it
                }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {

        return projectsDatabase.configDao().getConfig()
                .toSingle(Config(lastCacheTime = 0L))
                .map {
                    System.currentTimeMillis() - it.lastCacheTime > (60 * 10 * 1000).toLong()
                }
    }
}