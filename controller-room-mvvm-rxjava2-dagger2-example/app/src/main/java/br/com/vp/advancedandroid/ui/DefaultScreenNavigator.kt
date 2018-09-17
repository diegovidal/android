package br.com.vp.advancedandroid.ui

import android.support.v7.app.AppCompatActivity
import br.com.vp.advancedandroid.details.RepoDetailsController
import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.di.ScreenScope
import br.com.vp.advancedandroid.lifecycle.ActivityLifecycleTask
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import javax.inject.Inject

/**
 * @author diegovidal on 23/04/2018.
 */

@ActivityScope
class DefaultScreenNavigator @Inject
    constructor(): ActivityLifecycleTask(), ScreenNavigator {

    private var router: Router? = null

    fun initWithRouter(router: Router, rootScreen: Controller) {

        this.router = router
        if (!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen))
        }
    }

    override fun onCreate(activity: AppCompatActivity) {
        if (activity !is RouterProvider){
            throw  IllegalArgumentException("Activity must be instance of RouterProvider")
        }
        initWithRouter((activity as RouterProvider).getRouter(), (activity as RouterProvider).initialScreen())
    }

    override fun pop(): Boolean {

        return router != null && router?.handleBack()!!
    }

    override fun goToRepoDetails(repoOwner: String, repoName: String) {

        router?.pushController(RouterTransaction.with(RepoDetailsController.newInstance(repoName, repoOwner))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    override fun onDestroy(activity: AppCompatActivity) {
        router = null
    }
}