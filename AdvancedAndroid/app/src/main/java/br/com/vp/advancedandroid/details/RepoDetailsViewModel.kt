package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import javax.inject.Inject

/**
 * @author diegovidal on 02/05/2018.
 */

@ScreenScope
class RepoDetailsViewModel @Inject constructor(){

    private val detailsStateRelay= BehaviorRelay.create<RepoDetailsState>()
    private val contributorStateRelay = BehaviorRelay.create<ContributorState>()

    init {

        detailsStateRelay.accept(RepoDetailsState(true))
        contributorStateRelay.accept(ContributorState(true))
    }

    fun details(): Observable<RepoDetailsState> {
        return detailsStateRelay
    }

    fun contributors(): Observable<ContributorState> {
        return contributorStateRelay
    }

    fun processRepo(): Consumer<Repo> {

        return Consumer({ repo ->

            detailsStateRelay.accept(
                    RepoDetailsState(false,
                            repo.name,
                            repo.description,
                            repo.createdDate.format(DATE_TIME_FORMATTER),
                            repo.updatedDate.format(DATE_TIME_FORMATTER))
            )
        })
    }

    fun processContributors(): Consumer<List<Contributor>> {

        return Consumer({ contributors ->

            contributorStateRelay.accept(
                    ContributorState(false,
                            contributors)
            )
        })
    }

    fun detailsError(): Consumer<Throwable> {

        return Consumer({ throwable ->

            Timber.e(throwable, "Error loading repo details")
            detailsStateRelay.accept(
                    RepoDetailsState(false,
                            errorRes = R.string.api_error_single_repo)
            )
        })
    }

    fun contributorsError(): Consumer<Throwable> {

        return Consumer({ throwable ->

            Timber.e(throwable, "Error loading contributors")
            contributorStateRelay.accept(
                    ContributorState(false,
                            errorRes = R.string.api_error_contributors)
            )
        })
    }

    companion object {

        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    }
}