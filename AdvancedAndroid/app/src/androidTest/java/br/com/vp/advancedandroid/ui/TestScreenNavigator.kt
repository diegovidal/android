package br.com.vp.advancedandroid.ui

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author diegovidal on 08/05/2018.
 */

@Singleton
class TestScreenNavigator @Inject
        constructor(private val screenNavigator: DefaultScreenNavigator)
    : ScreenNavigator {

    private var overrideController: Controller? = null

    /**
     * Set the Controller to launch when the Activity attaches to the ScreenNavigator. This will
     * be used instead of the Controller passed in to {@link ScreenNavigator#initWithRouter(Router, Controller)}
     *
     * @param overrideController Controller to launch when Activity starts. Or null to fall back to default.
     */
    fun overrideInitialController(overrideController: Controller) {
        this.overrideController = overrideController
    }

    override fun initWithRouter(router: Router, rootScreen: Controller) {

        val launchController = overrideController?.let { overrideController } ?: rootScreen
        screenNavigator.initWithRouter(router, launchController)
    }

    override fun pop(): Boolean {

        return screenNavigator.pop()
    }

    override fun clear() {

        screenNavigator.clear()
    }

    override fun goToRepoDetails(repoOwner: String, repoName: String) {

        screenNavigator.goToRepoDetails(repoOwner, repoName)
    }
}