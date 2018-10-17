package com.dvidal.domain.interactor.bookmark

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.interactor.base.CompletableUseCase
import com.dvidal.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author diegovidal on 30/09/2018.
 */
class UnbookmarkProjectUseCase @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread

): CompletableUseCase<UnbookmarkProjectUseCase.Params>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return projectsRepository.unbookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String){
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}