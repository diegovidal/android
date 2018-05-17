package br.com.vp.advancedandroid.details

import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.ScreenLifecycleTask
import br.com.vp.advancedandroid.poweradapter.adapter.RecyclerDataSource
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import br.com.vp.advancedandroid.poweradapter.item.RecyclerItem
import br.com.vp.advancedandroid.poweradapter.item.RenderKey
import br.com.vp.advancedandroid.trending.RepoRenderer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

/**
 * @author diegovidal on 10/05/2018.
 */

@Module
abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract fun bindUiManagerTask(uiManager: RepoDetailsUiManager): ScreenLifecycleTask

    @Binds
    @IntoMap
    @RenderKey("Contributor")
    abstract fun bindContributorRenderer(renderer: ContributorRenderer): ItemRenderer<out RecyclerItem>

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ScreenScope
        fun provideRecyclerDataSource(renderers: MutableMap<String, ItemRenderer<out RecyclerItem>>): RecyclerDataSource{
            return RecyclerDataSource(renderers)
        }
    }
}