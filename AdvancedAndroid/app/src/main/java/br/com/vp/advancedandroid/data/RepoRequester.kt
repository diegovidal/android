package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.model.Repo
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author diegovidal on 24/04/2018.
 */

class RepoRequester @Inject constructor(private val service: RepoService) {

    fun getTrendingRepos(): Single<List<Repo>>?{

        return service.getTrendingRepos()
                ?.map(TrendingReposResponse::repos)
                ?.subscribeOn(Schedulers.io())
    }
}