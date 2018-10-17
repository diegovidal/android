package com.dvidal.domain.repository

import com.dvidal.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author diegovidal on 30/09/2018.
 */
interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}