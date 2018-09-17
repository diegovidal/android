package br.com.vp.plantplacespackt

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.junit.Rule
import org.junit.Test
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith

import android.support.test.rule.ActivityTestRule
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.not


/**
 * @author diegovidal on 10/09/2018.
 */

@RunWith(AndroidJUnit4::class)
class SearchPlantsActivityTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(SearchPlantsActivity::class.java)

    @Test
    fun searchForRedbudShouldReturnAtLeastOneResult() {

        val context = InstrumentationRegistry.getContext()

        onView(withId(R.id.actPlants)).perform(typeText("Redbud"))
        onView(withId(R.id.btnSearchPlants)).perform(click())

        val plants = activityTestRule.activity.mPlants
        assertThat(plants, not(empty()))
    }


}