package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

/**
 * @author diegovidal on 10/05/2018.
 */

@Module
abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract fun bindUiManagerTask(uiManager: RepoDetailsUiManager): ScreenLifecycleTask
}