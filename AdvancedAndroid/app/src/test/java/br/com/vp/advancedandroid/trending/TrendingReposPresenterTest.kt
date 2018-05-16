package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.data.TrendingReposResponse
import br.com.vp.advancedandroid.lifecycle.DisposableManager
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.poweradapter.adapter.RecyclerDataSource
import br.com.vp.advancedandroid.testutils.TestUtils
import br.com.vp.advancedandroid.ui.ScreenNavigator
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
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

    companion object {

        init {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable -> Schedulers.trampoline() }
        }
    }

    @Mock private lateinit var repoRepository: RepoRepository
    @Mock private lateinit var viewModel: TrendingReposViewModel
    @Mock private lateinit var onErrorConsumer: Consumer<Throwable>
    @Mock private lateinit var loadingConsumer: Consumer<Boolean>
    @Mock private lateinit var screenNavigator: ScreenNavigator
    @Mock private lateinit var dataSource: RecyclerDataSource

    private var presenter: TrendingReposPresenter? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        whenever(viewModel.loadingUpdated()).thenReturn(loadingConsumer)
        whenever(viewModel.onError()).thenReturn(onErrorConsumer)
        whenever(viewModel.reposUpdated()).thenReturn(Action {  })
    }

    @Test
    fun onRepoClicked() {

        val repo = TestUtils.loadJson("mock/repos/get_repo", Repo::class.java)
        setUpOnSuccess()
        initPresenter()

        repo?.let {

            presenter?.onRepoClicked(repo)
            verify(screenNavigator).goToRepoDetails(repo.owner.login, repo.name)
        }

    }

    @Test
    fun reposLoaded() {

        val repos = setUpOnSuccess()
        initPresenter()

        verify(repoRepository).getTrendingRepos()
        repos?.let { verify(dataSource).setData(it) }
        verifyZeroInteractions(onErrorConsumer)

    }

    @Test
    fun reposLoadedError() {

        val error = setUpOnError()
        initPresenter()

        verify(onErrorConsumer).accept(error)
        verifyZeroInteractions(dataSource)
    }

    @Test
    fun loadingSuccess() {

        setUpOnSuccess()
        initPresenter()

        val inOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer).accept(true)
        inOrder.verify(loadingConsumer).accept(false)
    }

    @Test
    fun loadingError() {

        setUpOnError()
        initPresenter()

        val inOrder = Mockito.inOrder(loadingConsumer)
        inOrder.verify(loadingConsumer).accept(true)
        inOrder.verify(loadingConsumer).accept(false)
    }

    private fun setUpOnSuccess(): List<Repo>? {

        val response = TestUtils.loadJson("mock/search/get_trending_repos", TrendingReposResponse::class.java)
        val repos = response?.repos

        whenever(repoRepository.getTrendingRepos()).thenReturn(Single.just(repos))
        return repos
    }

    private fun setUpOnError(): Throwable {

        val error = IOException()
        whenever(repoRepository.getTrendingRepos()).thenReturn(Single.error(error))

        return error
    }

    private fun initPresenter(){

        presenter = TrendingReposPresenter(viewModel, repoRepository, screenNavigator,
                dataSource, Mockito.mock(DisposableManager::class.java))
    }
}