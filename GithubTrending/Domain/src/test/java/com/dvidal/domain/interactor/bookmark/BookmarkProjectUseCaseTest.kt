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
class BookmarkProjectUseCaseTest {

    private lateinit var bookmarkProjectUseCase: BookmarkProjectUseCase

    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        bookmarkProjectUseCase = BookmarkProjectUseCase(projectsRepository, postExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes() {

        stubBookmarkProject(Completable.complete())
        val testCompletable = bookmarkProjectUseCase.buildUseCaseCompletable(
                BookmarkProjectUseCase.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        bookmarkProjectUseCase.buildUseCaseCompletable().test()
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(projectsRepository.bookmarkProject(any()))
                .thenReturn(completable)
    }

}