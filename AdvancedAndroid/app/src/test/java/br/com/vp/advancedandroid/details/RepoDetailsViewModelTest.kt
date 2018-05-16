package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.testutils.TestUtils
import com.squareup.moshi.Types
import org.junit.Before
import org.junit.Test

import java.io.IOException

/**
 * @author diegovidal on 02/05/2018.
 */
class RepoDetailsViewModelTest {

    private lateinit var viewModel: RepoDetailsViewModel

    private val repo = TestUtils.loadJson("mock/repos/get_repo", Repo::class.java)

    @Before
    fun setUp() {
        viewModel = RepoDetailsViewModel()
    }

    @Test
    fun details() {

        viewModel.processRepo().accept(repo)
        viewModel.details().test().assertValue(
                RepoDetailsState(false,
                        "RxJava",
                        "RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based " +
                                "programs using observable sequences for the Java VM.",
                        "jan 08, 2013",
                        "out 06, 2017")
        )
    }

    @Test
    fun detailsError() {

        viewModel.detailsError().accept(IOException())
        viewModel.details().test().assertValue(
                RepoDetailsState(false,
                        errorRes = R.string.api_error_single_repo)
        )
    }

    @Test
    fun contributors() {

        viewModel.contributorsLoaded().accept(Any())
        viewModel.contributors().test().assertValue(
                ContributorState(false)
        )
    }

    @Test
    fun contributorsError() {

        viewModel.contributorsError().accept(IOException())
        viewModel.contributors().test().assertValue(
                ContributorState(false,
                        errorRes = R.string.api_error_contributors)
        )
    }
}