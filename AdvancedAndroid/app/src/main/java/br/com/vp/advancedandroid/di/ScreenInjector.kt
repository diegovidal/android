package br.com.vp.advancedandroid.di

import android.app.Activity
import br.com.vp.advancedandroid.base.BaseActivity
import br.com.vp.advancedandroid.base.BaseController
import com.bluelinelabs.conductor.Controller
import dagger.android.AndroidInjector
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @author diegovidal on 19/04/2018.
 */

@JvmSuppressWildcards
class ScreenInjector @Inject constructor(
        private var screenInjectors: Map<Class<out Controller>,
        Provider<AndroidInjector.Factory<out Controller>>>) {

    private val cache = HashMap<String, AndroidInjector<out Controller>>()

    fun inject(controller: Controller){

        if (controller !is BaseController){
            throw IllegalArgumentException("controller must extend BaseController")
        }

        val instanceId = controller.instanceId
        if (cache.containsKey(instanceId)){

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

        cache.remove(controller.instanceId)
    }

    companion object {

        fun get(activity: Activity): ScreenInjector?{

            if (activity !is BaseActivity){
                throw IllegalArgumentException("Controller must extend BaseActivity")
            }

            return activity.screenInjector
        }
    }
}