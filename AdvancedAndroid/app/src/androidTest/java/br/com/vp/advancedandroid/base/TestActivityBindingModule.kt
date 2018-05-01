package br.com.vp.advancedandroid.base

import android.app.Activity
import br.com.vp.advancedandroid.di.ControllerKey
import br.com.vp.advancedandroid.home.MainActivity
import br.com.vp.advancedandroid.home.TestMainActivityComponent
import br.com.vp.advancedandroid.trending.TrendingReposComponent
import br.com.vp.advancedandroid.trending.TrendingReposController
import com.bluelinelabs.conductor.Controller
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author diegovidal on 30/04/2018.
 */

@Module(subcomponents = [
    TestMainActivityComponent::class
])
abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjector(builder: TestMainActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>
}