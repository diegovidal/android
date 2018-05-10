package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.data.TrendingReposResponse
import br.com.vp.advancedandroid.testutils.TestUtils
import org.junit.Before
import org.junit.Test

import java.io.IOException

/**
 * @author diegovidal on 30/04/2018.
 */
class TrendingReposViewModelTest {

    private lateinit var viewModel: TrendingReposViewModel

    @Before
    fun setUp() {
        viewModel = TrendingReposViewModel()
    }

    @Test
    fun loading() {
        val loadingObserver = viewModel.loading().test()
        viewModel.loadingUpdated().accept(true)
        viewModel.loadingUpdated().accept(false)

        loadingObserver.assertValues(true, false)
    }

    @Test
    fun repos() {
        val response = TestUtils.loadJson("mock/search/get_trending_repos", TrendingReposResponse::class.java)
        viewModel.reposUpdated().accept(response?.repos)

        viewModel.repos().test().assertValue(response?.repos)
    }

    @Test
    fun error() {
        val errorObserver = viewModel.error().test()
        viewModel.onError().accept(IOException())

        viewModel.reposUpdated().accept(emptyList())

        errorObserver.assertValues(R.string.api_error_repos, -1)
    }
}