package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.test.TestUtils
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author diegovidal on 30/04/2018.
 */

@Singleton
class TestRepoService @Inject constructor(val testUtils: TestUtils)
    : RepoService {

    var sendError = false

    override fun getTrendingRepos(): Single<TrendingReposResponse>? {

        if (!sendError){
            val response = testUtils.loadJson("mock/get_trending_repos", TrendingReposResponse::class.java)
            return Single.just(response)
        }

        return Single.error(IOException())
    }
}