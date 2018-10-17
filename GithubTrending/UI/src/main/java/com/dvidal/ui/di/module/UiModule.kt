package com.dvidal.ui.di.module

import com.dvidal.domain.executor.PostExecutionThread
import com.dvidal.ui.features.bookmarked.BookmarkedActivity
import com.dvidal.ui.infrastructure.UiThread
import com.dvidal.ui.features.browse.BrowseActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author diegovidal on 02/10/2018.
 */

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}