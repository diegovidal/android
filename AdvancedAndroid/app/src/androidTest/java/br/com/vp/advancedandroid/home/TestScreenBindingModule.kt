package br.com.vp.advancedandroid.home

import br.com.vp.advancedandroid.di.ControllerKey
import br.com.vp.advancedandroid.trending.TrendingReposComponent
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
    TrendingReposComponent::class
])
abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController::class)
    abstract fun bindTrendingReposInjector(builder: TrendingReposComponent.Builder):
            AndroidInjector.Factory<out Controller>
}