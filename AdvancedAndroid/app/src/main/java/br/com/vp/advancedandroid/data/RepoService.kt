package br.com.vp.advancedandroid.data

import io.reactivex.Single
import retrofit2.http.GET

/**
 * @author diegovidal on 24/04/2018.
 */

interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    fun getTrendingRepos(): Single<TrendingReposResponse>?
}