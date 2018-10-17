package com.dvidal.ui.di

import android.app.Application
import com.dvidal.domain.repository.ProjectsRepository
import com.dvidal.ui.di.module.*
import com.dvidal.ui.infrastructure.MyApplication
import com.dvidal.ui.test.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author diegovidal on 02/10/2018.
 */

@Singleton
@Component( modules = [
    AndroidInjectionModule::class,
    TestApplicationModule::class,
    TestCacheModule::class,
    TestDataModule::class,
    PresentationModule::class,
    UiModule::class,
    TestRemoteModule::class
])
interface TestApplicationComponent {

    fun projectsRepository(): ProjectsRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)
}