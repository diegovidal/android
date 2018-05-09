package br.com.vp.advancedandroid.trending

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.data.TestRepoService
import br.com.vp.advancedandroid.test.ControllerTest
import com.bluelinelabs.conductor.Controller
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author diegovidal on 01/05/2018.
 */

@RunWith(AndroidJUnit4::class)
class TrendingReposControllerTest: ControllerTest(){

    @Test
    fun loadRepos() {

        repoService?.clearErrorFlags()
        launch()

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.repo_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        onView(allOf(withId(R.id.tv_repo_name), withText("RxJava"))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun loadReposError() {

        repoService?.errorFlags = TestRepoService.FLAG_TRENDING_REPOS
        launch()

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.repo_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

        onView(withId(R.id.tv_error)).check(matches(allOf(withText(R.string.api_error_repos),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))))
    }

    override fun controllerToLaunch(): Controller {

        return TrendingReposController()
    }
}