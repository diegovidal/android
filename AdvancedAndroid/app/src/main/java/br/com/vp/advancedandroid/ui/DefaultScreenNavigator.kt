package br.com.vp.advancedandroid.ui

import br.com.vp.advancedandroid.di.ActivityScope
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import javax.inject.Inject

/**
 * @author diegovidal on 23/04/2018.
 */

@ActivityScope
class DefaultScreenNavigator: ScreenNavigator {

    private var router: Router? = null

    @Inject
    constructor(){}

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
}