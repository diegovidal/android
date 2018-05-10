package br.com.vp.advancedandroid.data

import android.os.Handler
import android.os.Looper
import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.test.TestUtils
import com.squareup.moshi.Types
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author diegovidal on 30/04/2018.
 */

@Singleton
class TestRepoService @Inject
        constructor(private val testUtils: TestUtils)
    : RepoService {

    var errorFlags = 0
    var holdFlags = 0

    override fun getTrendingRepos(): Single<TrendingReposResponse> {

        if ((errorFlags and FLAG_TRENDING_REPOS) == 0){
            val response = testUtils.loadJson("mock/search/get_trending_repos",
                    TrendingReposResponse::class.java)

            if ((holdFlags and FLAG_TRENDING_REPOS) == 0){
                return holdingSingle(response, FLAG_TRENDING_REPOS)
            }
            return Single.just(response)
        }
        return Single.error(IOException())
    }

    override fun getRepo(repoOwner: String, repoName: String): Single<Repo> {

        if ((errorFlags and FLAG_GET_REPO) == 0){
            val repo = testUtils.loadJson("mock/repos/get_repo", Repo::class.java)

            if ((holdFlags and FLAG_GET_REPO) == FLAG_GET_REPO){
                return holdingSingle(repo, FLAG_GET_REPO)
            }
            return Single.just(repo)
        }
        return Single.error(IOException())
    }

    override fun getContributors(url: String): Single<List<Contributor>> {

        if ((errorFlags and FLAG_GET_CONTRIBUTORS) == 0){
            val contributors = testUtils.loadJson<List<Contributor>>("mock/repos/contributors/get_contributors",
                    Types.newParameterizedType(List::class.java, Contributor::class.java))

            if ((holdFlags and FLAG_GET_CONTRIBUTORS) == FLAG_GET_CONTRIBUTORS){
                return holdingSingle(contributors, FLAG_GET_CONTRIBUTORS)
            }
            return Single.just(contributors)
        }
        return Single.error(IOException())
    }

    fun clearErrorFlags(){
        this.errorFlags = 0
    }

    fun clearHoldFlags(){
        this.holdFlags = 0
    }

    private fun <T> holdingSingle(result: T?, flag: Int): Single<T>{

        return Single.create{ e ->

            val handler = Handler(Looper.getMainLooper())
            object : Runnable {
                override fun run() {

                    if ((holdFlags and flag) == flag){
                        handler.postDelayed(this, 50)
                    } else {
                        result?.let {
                            e.onSuccess(it)
                        }
                    }
                }
            }.run()
        }
    }

    companion object {

        const val FLAG_TRENDING_REPOS = 1
        const val FLAG_GET_REPO = 2
        const val FLAG_GET_CONTRIBUTORS = 4
    }
}