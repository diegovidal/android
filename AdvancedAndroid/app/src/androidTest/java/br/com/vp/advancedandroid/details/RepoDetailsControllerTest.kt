package br.com.vp.advancedandroid.details

import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.data.TestRepoService
import br.com.vp.advancedandroid.test.ControllerTest
import com.bluelinelabs.conductor.Controller
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author diegovidal on 08/05/2018.
 */

@RunWith(AndroidJUnit4::class)
class RepoDetailsControllerTest: ControllerTest() {

    @Before
    fun setUp() {
        testRule.clearState()
    }

    @Test
    fun loadingRepo() {

        repoService?.holdFlags = TestRepoService.FLAG_GET_REPO
        launch()
        RepoDetailsRobot
                .verifyLoadingVisibility(ViewMatchers.Visibility.VISIBLE)
    }

    @Test
    fun loadingContributors() {

        repoService?.holdFlags = TestRepoService.FLAG_GET_CONTRIBUTORS
        launch()
        RepoDetailsRobot
                .verifyContributorsLoadingVisibility(ViewMatchers.Visibility.VISIBLE)
    }

    @Test
    fun repoDetailsSuccess() {

        launch()
        RepoDetailsRobot
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyName("RxJava")
                .verifyDescription("RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.")
                .verifyCreatedDate("Jan 08, 2013")
                .verifyUpdatedDate("Oct 06, 2017")
    }

    @Test
    fun repoDetailsError() {

        repoService?.errorFlags = TestRepoService.FLAG_GET_REPO
        launch()
        RepoDetailsRobot
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContentVisibility(ViewMatchers.Visibility.GONE)
                .verifyErrorText(R.string.api_error_single_repo)
    }

    @Test
    fun contributorsSuccess() {

        launch()
        RepoDetailsRobot
                .verifyContributorsLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsErrorVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorShown("benjchristensen")
    }

    @Test
    fun contributorsError() {

        repoService?.errorFlags = TestRepoService.FLAG_GET_CONTRIBUTORS
        launch()
        RepoDetailsRobot
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsErrorText(R.string.api_error_contributors)
    }

    @Test
    fun repoSuccessContributorsError() {

        repoService?.errorFlags = TestRepoService.FLAG_GET_CONTRIBUTORS
        launch()
        RepoDetailsRobot
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsErrorText(R.string.api_error_contributors)
                .verifyErrorVisibility(ViewMatchers.Visibility.GONE)

    }

    override fun controllerToLaunch(): Controller {
        return RepoDetailsController.newInstance("ReactiveX", "RxJava")
    }
}