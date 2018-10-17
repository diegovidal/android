package com.dvidal.data.repository

import com.dvidal.data.model.ProjectEntity
import io.reactivex.Observable

/**
 * @author diegovidal on 30/09/2018.
 */
interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}