package br.com.vp.advancedandroid.home

import android.app.Activity
import br.com.vp.advancedandroid.di.ControllerKey
import com.bluelinelabs.conductor.Controller
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author diegovidal on 18/04/2018.
 */

//@Module(subcomponents = [
//    TrendingReposComponent::class
//])
//abstract class MainScreenBindingModule {
//
//    @Binds
//    @IntoMap
//    @ControllerKey(TrendingReposController::class)
//    abstract fun bindingTrendingReposInjector(builder: TrendingReposComponent.Builder):
//            AndroidInjector.Factory<out Controller>
//}