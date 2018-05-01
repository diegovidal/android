package br.com.vp.advancedandroid.data

import dagger.Binds
import dagger.Module

/**
 * @author diegovidal on 30/04/2018.
 */

@Module
abstract class TestRepoServiceModule {

    @Binds
    abstract fun bindRepoService(testRepoService: TestRepoService): RepoService
}