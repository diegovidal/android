package com.dvidal.domain.interactor.bookmark

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.domain.model.Project
import com.dvidal.domain.repository.ProjectsRepository
import com.dvidal.domain.utils.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @author diegovidal on 30/09/2018.
 */

@RunWith(JUnit4::class)
class GetBookmarkedProjectsUseCaseTest {

    private lateinit var getBookmarkedProjectsUseCase: GetBookmarkedProjectsUseCase

    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        getBookmarkedProjectsUseCase = GetBookmarkedProjectsUseCase(projectsRepository, postExecutionThread)
    }

    @Test
    fun getBookmarkedProjectsCompletes() {

        stubGetBookmarkedProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getBookmarkedProjectsUseCase.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {

        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetBookmarkedProjects(Observable.just(projects))
        val testObserver = getBookmarkedProjectsUseCase.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetBookmarkedProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getBookmarkedProjects())
                .thenReturn(observable)
    }
}