package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.di.ForScreen
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.DisposableManager
import br.com.vp.advancedandroid.poweradapter.adapter.RecyclerDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import javax.inject.Inject
import javax.inject.Named

/**
 * @author diegovidal on 07/05/2018.
 */

@ScreenScope
class RepoDetailsPresenter @Inject
        constructor(@Named("repo_owner") repoOwner: String,
                    @Named("repo_name") repoName: String,
                    repoRepository: RepoRepository,
                    viewModel: RepoDetailsViewModel,
                    dataSource: RecyclerDataSource,
                    @ForScreen disposableManager: DisposableManager) {

    init {

        disposableManager.add(repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap { repo -> repoRepository.getContributors(repo.contributorsUrl) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(dataSource::setData)
                .doOnError(viewModel.contributorsError())
                .subscribe(viewModel.contributorsLoaded(), Consumer { throwable ->

                    // We handle logging in the view model
                }))
    }
}