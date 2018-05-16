package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author diegovidal on 24/04/2018.
 */

class RepoRequester @Inject
        constructor(private val service: RepoService) {

    internal fun getTrendingRepos(): Single<List<Repo>>?{

        return service.getTrendingRepos()
                .map(TrendingReposResponse::repos)
    }

    internal fun getRepo(repoOwner: String, repoName: String): Single<Repo>? {

        return service.getRepo(repoOwner, repoName)
    }

    internal fun getContributors(url: String): Single<List<Contributor>>? {
        return service.getContributors(url)
    }
}