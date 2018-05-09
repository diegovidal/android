package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @author diegovidal on 01/05/2018.
 */

@Singleton
class RepoRepository @Inject
        constructor(private val repoRequesterProvider: Provider<RepoRequester>,
                    @Named("network_scheduler") private val scheduler: Scheduler){

    private val cachedTrendingRepos = mutableListOf<Repo>()
    private val cachedContributors = mutableMapOf<String, List<Contributor>>()

    fun getTrendingRepos(): Single<List<Repo>> {

        return Maybe.concat(cachedTrendingRepos(), apiTrendingRepos())
                .firstOrError()
                .subscribeOn(scheduler)
    }

    fun getRepo(repoOwner: String, repoName: String): Single<Repo> {

        return Maybe.concat(cachedRepo(repoOwner, repoName), apiRepo(repoOwner, repoName))
                .firstOrError()
                .subscribeOn(scheduler)
    }

    fun getContributors(url: String): Single<List<Contributor>> {

        return Maybe.concat(cachedContributors(url), apiContributors(url))
                .firstOrError()
                .subscribeOn(scheduler)
    }

    private fun cachedContributors(url: String): Maybe<List<Contributor>> {

        return Maybe.create{e ->

            if (cachedContributors.containsKey(url)){
                e.onSuccess(cachedContributors[url]!!)
            }
            e.onComplete()
        }
    }

    private fun apiContributors(url: String): Maybe<List<Contributor>>?{

        return repoRequesterProvider.get().getContributors(url)
                ?.doOnSuccess { contributors ->
                    cachedContributors.put(url, contributors)
                }?.toMaybe()
    }

    private fun cachedRepo(repoOwner: String, repoName: String): Maybe<Repo> {

        return Maybe.create{ e ->

            for (cachedRepo: Repo in cachedTrendingRepos){
                if (cachedRepo.owner.login == repoOwner && cachedRepo.name == repoName){
                    e.onSuccess(cachedRepo)
                    break
                }
            }
            e.onComplete()
        }
    }

    private fun apiRepo(repoOwner: String, repoName: String): Maybe<Repo>? {

        return repoRequesterProvider.get().getRepo(repoOwner, repoName)
                ?.toMaybe()
    }

    private fun cachedTrendingRepos(): Maybe<List<Repo>> {

        return Maybe.create<List<Repo>> { e ->
            if (cachedTrendingRepos.isNotEmpty()){
                e.onSuccess(cachedTrendingRepos)
            }
            e.onComplete()
        }
    }

    private fun apiTrendingRepos(): Maybe<List<Repo>>? {

        return repoRequesterProvider.get().getTrendingRepos()
                ?.doOnSuccess{repos ->
                    cachedTrendingRepos.clear()
                    cachedTrendingRepos.addAll(repos)
                }
                ?.toMaybe()
    }
}