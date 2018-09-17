package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.testutils.TestUtils
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.inject.Provider

/**
 * @author diegovidal on 01/05/2018.
 */
class RepoRepositoryTest {

    @Mock lateinit var repoRequesterProvider: Provider<RepoRequester>
    @Mock lateinit var repoRequester: RepoRequester

    private lateinit var repository: RepoRepository
    private var trendingReposResponse: TrendingReposResponse? = null

    private var rxJavaRepo: Repo? = null
    private var otherRepo: Repo? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(repoRequesterProvider.get()).thenReturn(repoRequester)

        trendingReposResponse = TestUtils.loadJson("mock/search/get_trending_repos", TrendingReposResponse::class.java)
        whenever(repoRequester.getTrendingRepos()).thenReturn(Single.just(trendingReposResponse?.repos))

        rxJavaRepo = trendingReposResponse?.repos?.get(0)
        otherRepo = trendingReposResponse?.repos?.get(1)
        repository = RepoRepository(repoRequesterProvider, Schedulers.trampoline())
    }

    @Test
    fun getTrendingRepos() {
        repository.getTrendingRepos().test().assertValue(trendingReposResponse?.repos)

        val modifiedList = ArrayList(trendingReposResponse?.repos)
        modifiedList.removeAt(0)
        whenever(repoRequester.getTrendingRepos()).thenReturn(Single.just(modifiedList))

        repository.getTrendingRepos().test().assertValue(trendingReposResponse?.repos)
    }

    @Test
    fun getRepo() {
        repository.getTrendingRepos().subscribe()

        whenever(repoRequester.getRepo(any(), any())).thenReturn(Single.just(otherRepo))

        repository.getRepo("ReactiveX", "RxJava").test().assertValue(rxJavaRepo)

        repository.getRepo("NotInCache", "NotInCache").test().assertValue(otherRepo)
    }
}