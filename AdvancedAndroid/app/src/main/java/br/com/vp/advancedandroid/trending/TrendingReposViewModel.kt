package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.model.Repo
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import timber.log.Timber
import javax.inject.Inject
import kotlin.concurrent.thread

/**
 * @author diegovidal on 26/04/2018.
 */

@ScreenScope
class TrendingReposViewModel @Inject constructor() {

    private val reposRelay: BehaviorRelay<List<Repo>> = BehaviorRelay.create()
    private val errorRelay: BehaviorRelay<Int> = BehaviorRelay.create()
    private val loadingRelay: BehaviorRelay<Boolean> = BehaviorRelay.create()

    fun loading(): Observable<Boolean> = loadingRelay

    fun repos(): Observable<List<Repo>> = reposRelay

    fun error(): Observable<Int> = errorRelay

    fun loadingUpdated(): Consumer<Boolean> = loadingRelay

    fun reposUpdated(): Consumer<List<Repo>> {

        errorRelay.accept(-1)
        return reposRelay
    }

    fun onError(): Consumer<Throwable> {

        return Consumer({ throwable ->

            Timber.e(throwable, "Error loading repos")
            errorRelay.accept(R.string.api_error_repos)
        })
    }
}