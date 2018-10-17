package com.dvidal.domain.interactor.bookmark

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.repository.ProjectsRepository
import com.dvidal.domain.utils.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author diegovidal on 30/09/2018.
 */
class UnbookmarkProjectUseCaseTest {

    private lateinit var unbookmarkProjectUseCase: UnbookmarkProjectUseCase

    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        unbookmarkProjectUseCase = UnbookmarkProjectUseCase(projectsRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkProjectCompletes() {

        stubUnbookmarkProject(Completable.complete())
        val testCompletable = unbookmarkProjectUseCase.buildUseCaseCompletable(
                UnbookmarkProjectUseCase.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectThrowsException() {
        unbookmarkProjectUseCase.buildUseCaseCompletable().test()
    }

    private fun stubUnbookmarkProject(completable: Completable) {
        whenever(projectsRepository.unbookmarkProject(any()))
                .thenReturn(completable)
    }
}