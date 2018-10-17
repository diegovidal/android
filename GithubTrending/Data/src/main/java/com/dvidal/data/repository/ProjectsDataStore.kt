package com.dvidal.data.repository

import com.dvidal.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author diegovidal on 30/09/2018.
 */
interface ProjectsDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

}