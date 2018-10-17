package com.dvidal.ui.features.browse

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dvidal.domain.model.Project
import com.dvidal.ui.R
import com.dvidal.ui.test.TestApplication
import com.dvidal.ui.utils.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author diegovidal on 02/10/2018.
 */

@RunWith(AndroidJUnit4::class)
class BrowseProjectsActivityTest {

    @get:Rule
    val activity = ActivityTestRule<BrowseActivity>(BrowseActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubProjectsRepositoryGetProjects(Observable.just(listOf(ProjectDataFactory.makeProject())))
        activity.launchActivity(null)
    }

    @Test
    fun projectsDisplay() {
        val projects = listOf(ProjectDataFactory.makeProject(),ProjectDataFactory.makeProject(),
                ProjectDataFactory.makeProject())
        stubProjectsRepositoryGetProjects(Observable.just(projects))
        activity.launchActivity(null)

        projects.forEachIndexed { index, project ->
            onView(withId(R.id.recycler_projects))
                    .perform(RecyclerViewActions.scrollToPosition<BrowseAdapter.ViewHolder>(index))

            onView(withId(R.id.recycler_projects))
                    .check(matches(hasDescendant(withText(project.fullName))))
        }
    }

    private fun stubProjectsRepositoryGetProjects(observable: Observable<List<Project>>) {

        whenever(TestApplication.appComponent().projectsRepository().getProjects())
                .thenReturn(observable)
    }
}