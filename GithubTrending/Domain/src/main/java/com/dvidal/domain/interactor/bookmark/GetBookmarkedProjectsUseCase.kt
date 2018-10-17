package com.dvidal.domain.interactor.bookmark

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.interactor.base.ObservableUseCase
import com.dvidal.domain.model.Project
import com.dvidal.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
class GetBookmarkedProjectsUseCase @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread

): ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}