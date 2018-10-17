package com.dvidal.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.dvidal.domain.interactor.bookmark.BookmarkProjectUseCase
import com.dvidal.domain.interactor.bookmark.UnbookmarkProjectUseCase
import com.dvidal.domain.interactor.browse.GetProjectsUseCase
import com.dvidal.domain.model.Project
import com.dvidal.presentation.mapper.ProjectViewMapper
import com.dvidal.presentation.model.ProjectView
import com.dvidal.presentation.state.ResourceState
import com.dvidal.presentation.utils.DataFactory
import com.dvidal.presentation.utils.ProjectFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

/**
 * @author diegovidal on 01/10/2018.
 */

@RunWith(JUnit4::class)
class BrowseProjectsViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    var getProjectsUseCase = mock<GetProjectsUseCase>()
    var bookmarkedProjectsUseCase = mock<BookmarkProjectUseCase>()
    var unbookmarkProjectUseCase = mock<UnbookmarkProjectUseCase>()
    var projectViewMapper = mock<ProjectViewMapper>()

    var projectsViewModel = BrowseProjectsViewModel(getProjectsUseCase, bookmarkedProjectsUseCase,
            unbookmarkProjectUseCase, projectViewMapper)

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun fetchProjectsExecutesUseCase() {

        projectsViewModel.fetchProjects()
        verify(getProjectsUseCase, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchProjectsReturnsSuccess() {

        val projects = ProjectFactory.makeProjectList(2)
        val projectsViews = ProjectFactory.makeProjectViewList(2)

        stubProjectMapperMapToView(projectsViews[0], projects[0])
        stubProjectMapperMapToView(projectsViews[1], projects[1])

        projectsViewModel.fetchProjects()

        verify(getProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(ResourceState.SUCCESS, projectsViewModel.getProjects().value?.status)
    }

    @Test
    fun fetchProjectsReturnsData() {

        val projects = ProjectFactory.makeProjectList(2)
        val projectsViews = ProjectFactory.makeProjectViewList(2)

        stubProjectMapperMapToView(projectsViews[0], projects[0])
        stubProjectMapperMapToView(projectsViews[1], projects[1])

        projectsViewModel.fetchProjects()

        verify(getProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(projectsViews, projectsViewModel.getProjects().value?.data)
    }

    @Test
    fun fetchProjectsReturnsError() {

        projectsViewModel.fetchProjects()

        verify(getProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, projectsViewModel.getProjects().value?.status)
    }

    @Test
    fun fetchProjectsReturnsMessageForError() {

        val errorMessage = DataFactory.randomString()

        projectsViewModel.fetchProjects()

        verify(getProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, projectsViewModel.getProjects().value?.message)
    }

    private fun stubProjectMapperMapToView(projectView: ProjectView,
                                           project: Project) {
        whenever(projectViewMapper.mapToView(project))
                .thenReturn(projectView)
    }
}