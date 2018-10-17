package com.dvidal.domain.interactor.browse

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
class GetProjectsUseCaseTest {

    private lateinit var getProjectsUseCase: GetProjectsUseCase

    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        getProjectsUseCase = GetProjectsUseCase(projectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletes() {

        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjectsUseCase.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {

        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(projects))
        val testObserver = getProjectsUseCase.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects())
                .thenReturn(observable)
    }
}