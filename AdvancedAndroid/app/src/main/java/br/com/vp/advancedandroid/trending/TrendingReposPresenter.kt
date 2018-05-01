package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.data.RepoRequester
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.model.Repo
import javax.inject.Inject

/**
 * @author diegovidal on 26/04/2018.
 */

@ScreenScope
class TrendingReposPresenter
    @Inject constructor(private val viewModel: TrendingReposViewModel,
                        private val repoRequester: RepoRequester):
        RepoAdapter.RepoClickedListener {

    init {
        loadRepos()
    }

    private fun loadRepos() {

        repoRequester.getTrendingRepos()
                ?.doOnSubscribe({ _ -> viewModel.loadingUpdated().accept(true) })
                ?.doOnEvent({d, t-> viewModel.loadingUpdated().accept(false)})
                ?.subscribe(viewModel.reposUpdated(), viewModel.onError())

    }

    override fun onRepoClicked(repo: Repo) {


    }
}