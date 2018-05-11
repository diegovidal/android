package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

/**
 * @author diegovidal on 10/05/2018.
 */

@Module
abstract class TrendingReposScreenModule {

    @Binds
    @IntoSet
    abstract fun bindUiManager(uiManager: TrendingReposUiManager): ScreenLifecycleTask
}