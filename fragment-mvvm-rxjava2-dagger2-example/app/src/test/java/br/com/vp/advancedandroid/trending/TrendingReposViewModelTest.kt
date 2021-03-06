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
    fun error() {
        val errorObserver = viewModel.error().test()
        viewModel.onError().accept(IOException())

        viewModel.reposUpdated().run()

        errorObserver.assertValues(R.string.api_error_repos, -1)
    }
}