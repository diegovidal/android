package br.com.vp.advancedandroid.trending

import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import br.com.vp.advancedandroid.poweradapter.adapter.RecyclerDataSource
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import br.com.vp.advancedandroid.poweradapter.item.RecyclerItem
import br.com.vp.advancedandroid.poweradapter.item.RenderKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

/**
 * @author diegovidal on 15/05/2018.
 */

@Module
abstract class TrendingReposScreenModule {

    @Binds
    @IntoSet
    internal abstract fun bindUiManager(uiManager: TrendingReposUiManager): ScreenLifecycleTask

    @Binds
    @IntoMap
    @RenderKey("Repo")
    internal abstract fun bindRepoRenderer(repoRenderer: RepoRenderer): ItemRenderer<out RecyclerItem>

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ScreenScope
        fun provideRecyclerDataSource(renderers: MutableMap<String, ItemRenderer<out RecyclerItem>>): RecyclerDataSource {
            return RecyclerDataSource(renderers)
        }
    }
}
