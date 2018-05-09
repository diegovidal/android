package br.com.vp.advancedandroid.ui

import br.com.vp.advancedandroid.details.RepoDetailsController
import br.com.vp.advancedandroid.di.ActivityScope
import br.com.vp.advancedandroid.di.ScreenScope
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import javax.inject.Inject

/**
 * @author diegovidal on 23/04/2018.
 */

class DefaultScreenNavigator @Inject
    constructor(): ScreenNavigator {

    private var router: Router? = null

    override fun initWithRouter(router: Router, rootScreen: Controller) {

        this.router = router
        if (!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen))
        }
    }

    override fun pop(): Boolean {

        return router != null && router?.handleBack()!!
    }

    override fun clear() {

        router = null
    }

    override fun goToRepoDetails(repoOwner: String, repoName: String) {

        router?.pushController(RouterTransaction.with(RepoDetailsController.newInstance(repoName, repoOwner))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }
}