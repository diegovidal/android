package br.com.vp.advancedandroid.home

import br.com.vp.advancedandroid.details.RepoDetailsComponent
import br.com.vp.advancedandroid.details.RepoDetailsController
import br.com.vp.advancedandroid.di.ControllerKey
import br.com.vp.advancedandroid.trending.TrendingReposController
import com.bluelinelabs.conductor.Controller
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author diegovidal on 30/04/2018.
 */

@Module(subcomponents = [
    TrendingReposComponent::class,
    RepoDetailsComponent::class
])
abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController::class)
    abstract fun bindTrendingReposInjector(builder: TrendingReposComponent.Builder):
            AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(RepoDetailsController::class)
    abstract fun bindRepoDetailsInjector(builder: RepoDetailsComponent.Builder):
            AndroidInjector.Factory<out Controller>
}