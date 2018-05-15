package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.di.ForScreen
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.DisposableManager
import br.com.vp.advancedandroid.model.Repo
import br.com.vp.advancedandroid.poweradapter.adapter.RecyclerDataSource
import br.com.vp.advancedandroid.ui.ScreenNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * @author diegovidal on 26/04/2018.
 */

@ScreenScope
class TrendingReposPresenter @Inject
        constructor(private val viewModel: TrendingReposViewModel,
                    private val repoRepository: RepoRepository,
                    private val screenNavigator: ScreenNavigator,
                    private val dataSource: RecyclerDataSource,
                    @ForScreen private val disposableManager: DisposableManager) {

    init {
        loadRepos()
    }

    private fun loadRepos() {

        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe({ _ -> viewModel.loadingUpdated().accept(true) })
                ?.doOnEvent({ _, _ -> viewModel.loadingUpdated().accept(false)})
                ?.doOnSuccess({_ -> viewModel.reposUpdated().run()})
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(Consumer<List<Repo>> { dataSource.setData(it) }, viewModel.onError()))

    }

    fun onRepoClicked(repo: Repo) {

        screenNavigator.goToRepoDetails(repo.owner.login, repo.name)
    }
}