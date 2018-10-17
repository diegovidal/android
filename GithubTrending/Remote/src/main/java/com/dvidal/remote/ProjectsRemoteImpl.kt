package com.dvidal.remote

import com.dvidal.data.model.ProjectEntity
import com.dvidal.data.repository.ProjectsRemote
import com.dvidal.remote.mapper.ProjectsResponseModelMapper
import com.dvidal.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */

class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper
    ): ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map { responseModel ->
                    responseModel.items.map { mapper.mapFromModel(it) }
                }
    }
}