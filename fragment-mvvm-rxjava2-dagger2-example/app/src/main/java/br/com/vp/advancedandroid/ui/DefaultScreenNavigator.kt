package br.com.vp.advancedandroid.ui

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import br.com.vp.advancedandroid.R
import br.com.vp.advancedandroid.details.RepoDetailsFragment
import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.lifecycle.ActivityLifecycleTask
import javax.inject.Inject

/**
 * @author diegovidal on 23/04/2018.
 */

@ActivityScope
class DefaultScreenNavigator @Inject
    constructor(): ActivityLifecycleTask(), ScreenNavigator {

    var fragmentManager: FragmentManager? = null

    override fun onCreate(activity: AppCompatActivity) {
        init(activity.supportFragmentManager, (activity as ScreenProvider).initialScreen())
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun init(fragmentManager: FragmentManager, rootScreen: Fragment) {

        this.fragmentManager = fragmentManager
        if (fragmentManager.fragments.size == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.screen_container, rootScreen)
                    .commit()
        }
    }

    override fun pop(): Boolean? {

        return fragmentManager?.popBackStackImmediate()
    }

    override fun goToRepoDetails(repoOwner: String, repoName: String) {

        fragmentManager?.beginTransaction()
                ?.replace(R.id.screen_container, RepoDetailsFragment.newInstance(repoName, repoOwner))
                ?.addToBackStack(null)
                ?.commit()
    }

    override fun onDestroy(activity: AppCompatActivity) {
        fragmentManager = null
    }
}