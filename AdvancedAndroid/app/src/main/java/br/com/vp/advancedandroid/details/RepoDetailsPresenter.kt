package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.di.ScreenScope
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
                    viewModel: RepoDetailsViewModel) {

    init {

        repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap { repo -> repoRepository.getContributors(repo.contributorsUrl) }
                .doOnError(viewModel.contributorsError())
                .subscribe(viewModel.processContributors(), Consumer { throwable ->

                    // We handle logging in the view model
                })
    }
}