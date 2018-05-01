package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.data.RepoRequester
import br.com.vp.advancedandroid.data.TrendingReposResponse
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.testutils.TestUtils
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * @author diegovidal on 30/04/2018.
 */
class TrendingReposPresenterTest {

    @Mock
    private lateinit var repoRequester: RepoRequester
    @Mock
    private lateinit var viewModel: TrendingReposViewModel
    @Mock
    private lateinit var onErrorConsumer: Consumer<Throwable>
    @Mock
    private lateinit var onSuccessConsumer: Consumer<List<Repo>>
    @Mock
    private lateinit var loadingConsumer: Consumer<Boolean>

    private var trendingReposPresenter: TrendingReposPresenter? = null
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        whenever(viewModel.loadingUpdated()).thenReturn(loadingConsumer)
        whenever(viewModel.onError()).thenReturn(onErrorConsumer)
        whenever(viewModel.reposUpdated()).thenReturn(onSuccessConsumer)
    }

    @Test
    fun onRepoClicked() {
    }

    @Test
    fun reposLoaded() {

        val repos = setUpOnSuccess()
        initializePresenter()

        verify(repoRequester).getTrendingRepos()
        verify(onSuccessConsumer).accept(repos)
        verifyZeroInteractions(onErrorConsumer)

    }

    @Test
    fun reposLoadedError() {

        val error = setUpOnError()
        initializePresenter()

        verify(onErrorConsumer).accept(error)
        verifyZeroInteractions(onSuccessConsumer)
    }

    @Test
    fun loadingSuccess() {

        setUpOnSuccess()
        initializePresenter()

        val inOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer).accept(true)
        inOrder.verify(loadingConsumer).accept(false)
    }

    @Test
    fun loadingError() {

        setUpOnError()
        initializePresenter()

        val inOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer).accept(true)
        inOrder.verify(loadingConsumer).accept(false)
    }

    private fun setUpOnSuccess(): List<Repo>? {

        val response = TestUtils.loadJson("mock/get_trending_repos", TrendingReposResponse::class.java)
        val repos = response?.repos

        whenever(repoRequester.getTrendingRepos()).thenReturn(Single.just(repos))
        return repos
    }

    private fun setUpOnError(): Throwable {

        val error = IOException()
        whenever(repoRequester.getTrendingRepos()).thenReturn(Single.error(error))

        return error
    }

    private fun initializePresenter(){

        trendingReposPresenter = TrendingReposPresenter(viewModel, repoRequester)
    }
}