package br.com.vp.advancedandroid.test

import android.content.Intent
import br.com.vp.advancedandroid.data.RepoRepository
import br.com.vp.advancedandroid.data.TestRepoService
import br.com.vp.advancedandroid.home.MainActivity
import br.com.vp.advancedandroid.ui.TestScreenNavigator
import com.bluelinelabs.conductor.Controller
import org.junit.Rule

/**
 * @author diegovidal on 08/05/2018.
 */
abstract class ControllerTest {

    @JvmField
    @Rule
    val testRule = ControllerTestRule(MainActivity::class.java)

    var screenNavigator: TestScreenNavigator? = null
    var repoService: TestRepoService? = null
    var repoRepository: RepoRepository? = null

    init {

        screenNavigator = testRule.testScreenNavigator
        repoService = testRule.testRepoService
        repoRepository = testRule.repoRepository

        screenNavigator?.overrideInitialController(this.controllerToLaunch())
    }

    protected abstract fun controllerToLaunch(): Controller

    internal fun launch() {
        launch(null)
    }

    private fun launch(intent: Intent?) {
        testRule.launchActivity(intent)
    }
}