package br.com.vp.advancedandroid.base

import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.data.TestRepoService
import br.com.vp.advancedandroid.data.TestRepoServiceModule
import br.com.vp.advancedandroid.networking.ServiceModule
import br.com.vp.advancedandroid.trending.TrendingReposControllerTest
import br.com.vp.advancedandroid.ui.NavigationModule
import br.com.vp.advancedandroid.ui.TestNavigationModule
import br.com.vp.advancedandroid.ui.TestScreenNavigator
import dagger.Component
import javax.inject.Singleton

/**
 * @author diegovidal on 30/04/2018.
 */


@Singleton
@Component(modules = [
    ApplicationModule::class,
    TestActivityBindingModule::class,
    ServiceModule::class,
    TestRepoServiceModule::class,
    TestNavigationModule::class
])
interface TestApplicationComponent: ApplicationComponent {

    fun inject(trendingReposControllerTest: TrendingReposControllerTest)

    fun screenNavigator(): TestScreenNavigator
    fun repoService(): TestRepoService
    fun repoRepository(): RepoRepository
}