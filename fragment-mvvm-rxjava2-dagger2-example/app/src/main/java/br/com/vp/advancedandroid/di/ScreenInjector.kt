package br.com.vp.advancedandroid.di

import android.app.Activity
import android.support.v4.app.Fragment
import br.com.vp.advancedandroid.base.BaseActivity
import br.com.vp.advancedandroid.base.BaseFragment
import dagger.android.AndroidInjector
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Provider

/**
 * @author diegovidal on 19/04/2018.
 */

@JvmSuppressWildcards
@ActivityScope
class ScreenInjector @Inject constructor(
        private var screenInjectors: Map<Class<out Fragment>,
        Provider<AndroidInjector.Factory<out Fragment>>>) {

    private val cache = HashMap<String?, AndroidInjector<out Fragment>>()

    fun inject(fragment: Fragment){

        if (fragment !is BaseFragment){
            throw IllegalArgumentException("fragment must extend BaseFragment")
        }

        val instanceId = fragment.arguments?.getString("instance_id")
        if (cache.containsKey(instanceId)){

            @Suppress("UNCHECKED_CAST")
            (cache[instanceId] as AndroidInjector<Fragment>).inject(fragment)
            return
        }

        @Suppress("UNCHECKED_CAST")
        val injectorFactory = screenInjectors[fragment.javaClass]?.get()
                as? AndroidInjector.Factory<Fragment>

        injectorFactory?.let {

            val injector = injectorFactory.create(fragment)
            cache[instanceId] = injector
            injector.inject(fragment)
        }
    }

    fun clear(fragment: Fragment){

        val injector = cache.remove(fragment.arguments?.getString("instance_id"))
        if (injector is ScreenComponent){
            injector.disposableManager().dispose()
        }
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