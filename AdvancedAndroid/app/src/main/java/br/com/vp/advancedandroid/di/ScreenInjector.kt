package br.com.vp.advancedandroid.di

import android.app.Activity
import android.app.Controller
import android.content.Context
import br.com.vp.advancedandroid.base.BaseActivity
import com.bluelinelabs.conductor.Controller
import dagger.android.AndroidInjector
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author diegovidal on 19/04/2018.
 */

@JvmSuppressWildcards //TODO: Only works with this signature
@ActivityScope
class ScreenInjector @Inject
internal constructor(private val screenInjectors: Map<Class<out Controller>,
        Provider<AndroidInjector.Factory<out Controller>>>) {

    private val cache = HashMap<String, AndroidInjector<out Controller>>()

    fun inject(controller: Controller){

        if (controller !is BaseActivity){
            throw IllegalArgumentException("controller must extend BaseActivity")
        }

        val instanceId = controller.instanceId
        if (cache.containsKey(instanceId)){

            @Suppress("UNCHECKED_CAST")
            (cache[instanceId] as AndroidInjector<Controller>).inject(controller)
            return
        }

        @Suppress("UNCHECKED_CAST")
        val injectorFactory = this.screenInjectors[controller.javaClass].get()
                as? AndroidInjector.Factory<Controller>

        injectorFactory?.let {

            val injector = injectorFactory.create(controller)
            cache[instanceId] = injector
            injector.inject(controller)
        }
    }

    fun clear(Controller: Controller){

        if (Controller !is BaseActivity){
            throw IllegalArgumentException("Controller must extend BaseActivity")
        }

        cache.remove(Controller.instanceId)
    }

    companion object {

        fun get(activity: Activity): ScreenInjector?{

            val baseActivity = activity as? BaseActivity ?: return null
            return baseActivity.screenInjector
        }
    }
}