package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.test.TestUtils
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author diegovidal on 30/04/2018.
 */

@Singleton
class TestRepoService @Inject constructor(private val testUtils: TestUtils)
    : RepoService {

    var sendError = false

    override fun getTrendingRepos(): Single<TrendingReposResponse> {

        if (!sendError){
            val response = testUtils.loadJson("mock/get_trending_repos", TrendingReposResponse::class.java)
            return Single.just(response)
        }

        return Single.error(IOException())
    }

    override fun getRepo(repoOwner: String, repoName: String): Single<Repo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContributors(url: String): Single<List<Contributor>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}