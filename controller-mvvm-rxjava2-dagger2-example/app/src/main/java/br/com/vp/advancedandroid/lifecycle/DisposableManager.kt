package br.com.vp.advancedandroid.lifecycle

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author diegovidal on 11/05/2018.
 */
class DisposableManager {

    private val compositeDisposable = CompositeDisposable()

    fun add(vararg disposables: Disposable?){
        this.compositeDisposable.addAll(*disposables)
    }

    fun dispose(){
        this.compositeDisposable.clear()
    }
}