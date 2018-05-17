package br.com.vp.advancedandroid.details

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import br.com.vp.advancedandroid.R
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers.allOf


/**
 * @author diegovidal on 08/05/2018.
 */
object RepoDetailsRobot {

    fun verifyName(name: String): RepoDetailsRobot{

        onView(withId(R.id.tv_repo_name)).check(matches(withText(name)))
        return this
    }

    fun verifyDescription(description: String): RepoDetailsRobot{

        onView(withId(R.id.tv_repo_description)).check(matches(withText(description)))
        return this
    }

    fun verifyCreatedDate(createdDate: String): RepoDetailsRobot{

        onView(withId(R.id.tv_creation_date)).check(matches(withText(createdDate)))
        return this
    }

    fun verifyUpdatedDate(updatedDate: String): RepoDetailsRobot {
        onView(withId(R.id.tv_updated_date)).check(matches(withText(updatedDate)))
        return this
    }

    fun verifyErrorText(textRes: Int?): RepoDetailsRobot {
        if (textRes == null) {
            onView(withId(R.id.tv_error)).check(matches(withText("")))
        } else {
            onView(withId(R.id.tv_error)).check(matches(withText(textRes)))
        }
        return this
    }

    fun verifyErrorVisibility(visibility: ViewMatchers.Visibility): RepoDetailsRobot {
        onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(visibility)))
        return this
    }

    fun verifyContributorsErrorText(textRes: Int?): RepoDetailsRobot {
        if (textRes == null) {
            onView(withText(R.id.tv_contributors_error)).check(matches(withText("")))
        } else {
            onView(withId(R.id.tv_contributors_error)).check(matches(withText(textRes)))
        }
        return this
    }

    fun verifyContributorsErrorVisibility(visibility: ViewMatchers.Visibility): RepoDetailsRobot {
        onView(withId(R.id.tv_contributors_error)).check(matches(withEffectiveVisibility(visibility)))
        return this
    }

    fun verifyLoadingVisibility(visibility: ViewMatchers.Visibility): RepoDetailsRobot {
        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(visibility)))
        return this
    }

    fun verifyContributorsLoadingVisibility(visibility: ViewMatchers.Visibility): RepoDetailsRobot {
        onView(withId(R.id.contributor_loading_indicator)).check(matches(withEffectiveVisibility(visibility)))
        return this
    }

    fun verifyContributorShown(login: String): RepoDetailsRobot {
        onView(allOf(withId(R.id.tv_user_name), withText(login))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        return this
    }

    fun verifyContentVisibility(visibility: ViewMatchers.Visibility): RepoDetailsRobot {
        onView(withId(R.id.content)).check(matches(withEffectiveVisibility(visibility)))
        return this
    }
}