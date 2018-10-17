package com.dvidal.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.dvidal.domain.interactor.bookmark.GetBookmarkedProjectsUseCase
import com.dvidal.domain.model.Project
import com.dvidal.presentation.mapper.ProjectViewMapper
import com.dvidal.presentation.model.ProjectView
import com.dvidal.presentation.state.ResourceState
import com.dvidal.presentation.utils.DataFactory
import com.dvidal.presentation.utils.ProjectFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import java.lang.RuntimeException

/**
 * @author diegovidal on 01/10/2018.
 */

@RunWith(JUnit4::class)
class BrowseBookmarkedProjectsViewModelTest {

    @get: Rule var intantTaskExecutorRule = InstantTaskExecutorRule()

    var getBookmarkedProjectsUseCase = mock<GetBookmarkedProjectsUseCase>()
    var mapper = mock<ProjectViewMapper>()

    var browseBookmarkedProjectsViewModel = BrowseBookmarkedProjectsViewModel(
            getBookmarkedProjectsUseCase, mapper
    )

    @Captor
    var captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun fetchProjectsExecutesUseCase() {

        browseBookmarkedProjectsViewModel.fetchBookmarkedProjects()
        verify(getBookmarkedProjectsUseCase, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchProjectsReturnsSuccess() {

        val projects = ProjectFactory.makeProjectList(2)
        val projectsViews = ProjectFactory.makeProjectViewList(2)

        stubProjectMapperMapToView(projectsViews[0], projects[0])
        stubProjectMapperMapToView(projectsViews[1], projects[1])

        browseBookmarkedProjectsViewModel.fetchBookmarkedProjects()

        verify(getBookmarkedProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(ResourceState.SUCCESS, browseBookmarkedProjectsViewModel
                .getBookmarkedProjects().value?.status)
    }

    @Test
    fun fetchProjectsReturnsData() {
        val projects = ProjectFactory.makeProjectList(2)
        val projectViews = ProjectFactory.makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        browseBookmarkedProjectsViewModel.fetchBookmarkedProjects()

        verify(getBookmarkedProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(projectViews,
                browseBookmarkedProjectsViewModel.getBookmarkedProjects().value?.data)
    }

    @Test
    fun fetchProjectsReturnsError() {

        browseBookmarkedProjectsViewModel.fetchBookmarkedProjects()

        verify(getBookmarkedProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR,
                browseBookmarkedProjectsViewModel.getBookmarkedProjects().value?.status)
    }

    @Test
    fun fetchProjectsReturnsMessageForError() {

        val errorMessage = DataFactory.randomString()
        browseBookmarkedProjectsViewModel.fetchBookmarkedProjects()

        verify(getBookmarkedProjectsUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage,
                browseBookmarkedProjectsViewModel.getBookmarkedProjects().value?.message)
    }

    private fun stubProjectMapperMapToView(projectView: ProjectView,
                                           project: Project) {
        whenever(mapper.mapToView(project))
                .thenReturn(projectView)
    }
}