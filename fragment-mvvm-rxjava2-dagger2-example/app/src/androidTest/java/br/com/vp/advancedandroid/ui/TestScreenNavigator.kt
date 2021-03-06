package br.com.vp.advancedandroid.ui

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.vp.advancedandroid.lifecycle.ActivityLifecycleTask
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author diegovidal on 08/05/2018.
 */

@Singleton
class TestScreenNavigator @Inject
        constructor()
    : ActivityLifecycleTask(), ScreenNavigator {

    private val defaultScreenNavigator = DefaultScreenNavigator()

    private var overrideController: Fragment? = null

    /**
     * Set the Controller to launch when the Activity attaches to the ScreenNavigator. This will
     * be used instead of the Controller provided by {@link RouterProvider#initialScreen()}
     *
     * @param overrideController Controller to launch when Activity starts. Or null to fall back to default.
     */
    fun overrideInitialController(overrideController: Fragment) {
        this.overrideController = overrideController
    }

    override fun onCreate(activity: AppCompatActivity) {
        if (activity !is ScreenProvider){
            throw  IllegalArgumentException("Activity must be instance of RouterProvider")
        }
        val launchController = overrideController?.let { overrideController  } ?: (activity as ScreenProvider).initialScreen()
        defaultScreenNavigator.init(activity.supportFragmentManager, launchController)
    }

    override fun pop(): Boolean? {

        return defaultScreenNavigator.pop()
    }

    override fun onDestroy(activity: AppCompatActivity) {
        defaultScreenNavigator.onDestroy(activity)
    }

    override fun goToRepoDetails(repoOwner: String, repoName: String) {

        defaultScreenNavigator.goToRepoDetails(repoOwner, repoName)
    }
}