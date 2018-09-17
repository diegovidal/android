package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.di.ScreenScope
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject

/**
 * @author diegovidal on 26/04/2018.
 */

@ScreenScope
class TrendingReposViewModel @Inject constructor() {

    private val errorRelay: BehaviorRelay<Int> = BehaviorRelay.create()
    private val loadingRelay: BehaviorRelay<Boolean> = BehaviorRelay.create()

    fun loading(): Observable<Boolean> = loadingRelay

    fun error(): Observable<Int> = errorRelay

    fun loadingUpdated(): Consumer<Boolean> = loadingRelay

    fun reposUpdated(): Action {

        return Action {
            errorRelay.accept(-1)
        }
    }

    fun onError(): Consumer<Throwable> {

        return Consumer({ throwable ->

            Timber.e(throwable, "Error loading repos")
            errorRelay.accept(R.string.api_error_repos)
        })
    }
}