package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.di.ForScreen
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.DisposableManager
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.ui.ScreenNavigator
import javax.inject.Inject

/**
 * @author diegovidal on 26/04/2018.
 */

@ScreenScope
class TrendingReposPresenter @Inject
        constructor(private val viewModel: TrendingReposViewModel,
                    private val repoRepository: RepoRepository,
                    private val screenNavigator: ScreenNavigator,
                    @ForScreen val disposableManager: DisposableManager)
        : RepoAdapter.RepoClickedListener {

    init {
        loadRepos()
    }

    private fun loadRepos() {

        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe({ _ -> viewModel.loadingUpdated().accept(true) })
                ?.doOnEvent({ _, _ -> viewModel.loadingUpdated().accept(false)})
                ?.subscribe(viewModel.reposUpdated(), viewModel.onError()))

    }

    override fun onRepoClicked(repo: Repo) {

        screenNavigator.goToRepoDetails(repo.owner.login, repo.name)
    }
}