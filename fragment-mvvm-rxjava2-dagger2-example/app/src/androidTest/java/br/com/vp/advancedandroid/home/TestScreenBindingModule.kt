package br.com.vp.advancedandroid.home

import android.support.v4.app.Fragment
import br.com.vp.advancedandroid.base.ScreenModule
import br.com.vp.advancedandroid.details.RepoDetailsComponent
import br.com.vp.advancedandroid.details.RepoDetailsFragment
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.trending.TrendingReposFragment
import br.com.vp.advancedandroid.trending.TrendingReposScreenModule
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

/**
 * @author diegovidal on 30/04/2018.
 */

@Module(subcomponents = [
    RepoDetailsComponent::class
])
abstract class TestScreenBindingModule {

    @ContributesAndroidInjector(modules = [
        ScreenModule::class,
        TrendingReposScreenModule::class
    ])
    @ScreenScope
    abstract fun bindTrendingReposInjector(): TrendingReposFragment

    @Binds
    @IntoMap
    @FragmentKey(RepoDetailsFragment::class)
    abstract fun bindRepoDetailsInjector(builder: RepoDetailsComponent.Builder): AndroidInjector.Factory<out Fragment>
}