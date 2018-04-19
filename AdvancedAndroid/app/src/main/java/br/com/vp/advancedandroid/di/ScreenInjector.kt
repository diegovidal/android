package br.com.vp.advancedandroid.di

import android.app.Activity
import br.com.vp.advancedandroid.base.BaseActivity
import br.com.vp.advancedandroid.base.BaseController
import com.bluelinelabs.conductor.Controller
import dagger.android.AndroidInjector
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author diegovidal on 18/04/2018.
 */

class ScreenInjector @Inject
internal constructor(private val screenInjectors: Map<Class<out Controller>,
        Provider<AndroidInjector.Factory<out Controller>>>) {

    private val cache = HashMap<String, AndroidInjector<out Controller>>()

    fun inject(controller: Controller) {

        if (controller !is BaseController) {
            throw IllegalArgumentException("Controller must extend BaseController")
        }

        val instanceId = controller.instanceId
        if (cache.containsKey(instanceId)) {

            @Suppress("UNCHECKED_CAST")
            (cache[instanceId] as AndroidInjector<Controller>).inject(controller)
            return
        }

        @Suppress("UNCHECKED_CAST")
        val injectorFactory = screenInjectors[controller.javaClass]?.get()
                as? AndroidInjector.Factory<Controller>

        injectorFactory?.let {

            val injector = injectorFactory.create(controller)
            cache[instanceId] = injector
            injector.inject(controller)
        }
    }

    fun clear(controller: Controller){

        if (controller !is BaseController){
            throw IllegalArgumentException("Controller must extend BaseController")
        }

        cache.remove(controller.instanceId)
    }

    companion object {

        fun get(activity: Activity): ScreenInjector?{

            val myAppContext = activity.applicationContext as? BaseActivity ?: return null
            return myAppContext.screenInjector
        }
    }
}

