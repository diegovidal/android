package com.dvidal.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dvidal.domain.interactor.bookmark.BookmarkProjectUseCase
import com.dvidal.domain.interactor.bookmark.UnbookmarkProjectUseCase
import com.dvidal.domain.interactor.browse.GetProjectsUseCase
import com.dvidal.domain.model.Project
import com.dvidal.presentation.mapper.ProjectViewMapper
import com.dvidal.presentation.model.ProjectView
import com.dvidal.presentation.state.Resource
import com.dvidal.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class BrowseProjectsViewModel @Inject constructor(
        private val getProjectsUseCase: GetProjectsUseCase,
        private val bookmarkProjectUseCase: BookmarkProjectUseCase,
        private val unbookmarkProjectUseCase: UnbookmarkProjectUseCase,
        private val mapper: ProjectViewMapper)
    : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {

        getProjectsUseCase.dispose()
        bookmarkProjectUseCase.dispose()
        unbookmarkProjectUseCase.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjectsUseCase.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId: String) {

        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return bookmarkProjectUseCase.execute(BookmarkProjectsSubscriber(),
                BookmarkProjectUseCase.Params.forProject(projectId))
    }

    fun unbookmarkProject(projectId: String) {

        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return unbookmarkProjectUseCase.execute(BookmarkProjectsSubscriber(),
                UnbookmarkProjectUseCase.Params.forProject(projectId))
    }

    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {

        override fun onComplete() {}

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR,
                    null, e.localizedMessage))
        }
    }

    inner class BookmarkProjectsSubscriber: DisposableCompletableObserver() {

        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR,
                    liveData.value?.data, e.localizedMessage))
        }
    }
}