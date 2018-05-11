package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.lifecycle.DisposableManager
import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.testutils.TestUtils
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.squareup.moshi.Types
import io.reactivex.Single
import io.reactivex.functions.Consumer
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * @author diegovidal on 07/05/2018.
 */
class RepoDetailsPresenterTest {

    private val OWNER = "owner"
    private val NAME = "name"

    @Mock lateinit var repoRepository: RepoRepository
    @Mock lateinit var viewModel: RepoDetailsViewModel
    @Mock lateinit var repoConsumer: Consumer<Repo>
    @Mock lateinit var contributorConsumer: Consumer<List<Contributor>>
    @Mock lateinit var detailsErrorConsumer: Consumer<Throwable>
    @Mock lateinit var contributorErrorConsumer: Consumer<Throwable>

    private val repo = TestUtils.loadJson("mock/repos/get_repo", Repo::class.java)
    private val contributors = TestUtils.loadJson<List<Contributor>>("mock/repos/contributors/get_contributors",
            Types.newParameterizedType(List::class.java, Contributor::class.java))
    private val contributorUrl = repo?.contributorsUrl ?: ""

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        whenever(viewModel.processRepo()).thenReturn(repoConsumer)
        whenever(viewModel.processContributors()).thenReturn(contributorConsumer)
        whenever(viewModel.detailsError()).thenReturn(detailsErrorConsumer)
        whenever(viewModel.contributorsError()).thenReturn(contributorErrorConsumer)

        whenever(repoRepository.getRepo(OWNER, NAME)).thenReturn(Single.just(repo))
        whenever(repoRepository.getContributors(contributorUrl)).thenReturn(Single.just(contributors))
    }

    @Test
    fun repoDetails() {
        initPresenter()

        verify(repoConsumer).accept(repo)
    }

    @Test
    fun repoDetailsError() {

        val t = IOException()
        whenever(repoRepository.getRepo(OWNER, NAME)).thenReturn(Single.error(t))
        initPresenter()

        verify(detailsErrorConsumer).accept(t)
    }

    @Test
    fun repoContributors() {
        initPresenter()

        verify(contributorConsumer).accept(contributors)
    }

    @Test
    fun repoContributorsError() {

        val t =IOException()
        whenever(repoRepository.getContributors(contributorUrl)).thenReturn(Single.error(t))
        initPresenter()

        verify(contributorErrorConsumer).accept(t)

        // Verify that our repo details were still processed even though the contributors failed to load
        verify(repoConsumer).accept(repo)
    }

    private fun initPresenter() {

        RepoDetailsPresenter(OWNER, NAME, repoRepository, viewModel, Mockito.mock(DisposableManager::class.java))
    }
}