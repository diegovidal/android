package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.ui.NavigationModule
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * @author diegovidal on 18/04/2018.
 */

@ScreenScope
@Subcomponent
interface TrendingReposComponent: AndroidInjector<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<TrendingReposController>()
}