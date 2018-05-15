package br.com.vp.advancedandroid.di

import br.com.vp.advancedandroid.lifecycle.DisposableManager
import dagger.android.AndroidInjector

/**
 * @author diegovidal on 11/05/2018.
 */

interface ScreenComponent<T> : AndroidInjector<T> {

    @ForScreen
    fun disposableManager(): DisposableManager
}