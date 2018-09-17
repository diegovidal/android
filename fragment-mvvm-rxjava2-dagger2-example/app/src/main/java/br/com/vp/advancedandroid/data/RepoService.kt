package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * @author diegovidal on 24/04/2018.
 */

interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    fun getTrendingRepos(): Single<TrendingReposResponse>

    @GET("repos/{owner}/{name}")
    fun getRepo(@Path("owner") repoOwner: String, @Path("name") repoName: String): Single<Repo>

    @GET
    fun getContributors(@Url url: String): Single<List<Contributor>>
}