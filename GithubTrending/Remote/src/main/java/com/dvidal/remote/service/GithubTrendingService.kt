package com.dvidal.remote.service

import com.dvidal.remote.model.ProjectsResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author diegovidal on 01/10/2018.
 */
interface GithubTrendingService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String)
            : Observable<ProjectsResponseModel>
}