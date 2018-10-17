package com.dvidal.data.repository

import com.dvidal.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author diegovidal on 30/09/2018.
 */
interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun areProjectsCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isProjectsCacheExpired(): Single<Boolean>
}