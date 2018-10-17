package com.dvidal.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.dvidal.domain.interactor.bookmark.GetBookmarkedProjectsUseCase
import com.dvidal.domain.model.Project
import com.dvidal.presentation.mapper.ProjectViewMapper
import com.dvidal.presentation.model.ProjectView
import com.dvidal.presentation.state.Resource
import com.dvidal.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
class BrowseBookmarkedProjectsViewModel @Inject constructor(
        private val getBookmarkedProjectsUseCase: GetBookmarkedProjectsUseCase,
        private val mapper: ProjectViewMapper)
    : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getBookmarkedProjectsUseCase.dispose()
        super.onCleared()
    }

    fun getBookmarkedProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchBookmarkedProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedProjectsUseCase.execute(BookmarkedProjectsSubscriber())
    }

    inner class BookmarkedProjectsSubscriber: DisposableObserver<List<Project>>() {

        override fun onComplete() {}

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}