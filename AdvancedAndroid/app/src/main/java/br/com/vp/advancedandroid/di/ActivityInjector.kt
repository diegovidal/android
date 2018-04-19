package br.com.vp.advancedandroid.di

import android.app.Activity
import android.content.Context
import br.com.vp.advancedandroid.base.BaseActivity
import br.com.vp.advancedandroid.base.MyApplication

import java.util.HashMap

import javax.inject.Inject
import javax.inject.Provider

import dagger.android.AndroidInjector

/**
 * @author diegovidal on 11/04/2018.
 */

@JvmSuppressWildcards //TODO: Only works with this signature
class ActivityInjector @Inject
internal constructor(private val activityInjectors: Map<Class<out Activity>,
        Provider<AndroidInjector.Factory<out Activity>>>) {

    private val cache = HashMap<String, AndroidInjector<out Activity>>()

    fun inject(activity: Activity){

        if (activity !is BaseActivity){
            throw IllegalArgumentException("Activity must extend BaseActivity")
        }

        val instanceId = activity.instanceId
        if (cache.containsKey(instanceId)){

            @Suppress("UNCHECKED_CAST")
            (cache[instanceId] as AndroidInjector<Activity>).inject(activity)
            return
        }

        @Suppress("UNCHECKED_CAST")
        val injectorFactory = activityInjectors[activity.javaClass]?.get()
                as? AndroidInjector.Factory<Activity>

        injectorFactory?.let {

            val injector = injectorFactory.create(activity)
            cache[instanceId] = injector
            injector.inject(activity)
        }
    }

    fun clear(activity: Activity){

        if (activity !is BaseActivity){
            throw IllegalArgumentException("Activity must extend BaseActivity")
        }

        cache.remove(activity.instanceId)
    }

    companion object {

        fun get(context: Context): ActivityInjector?{

            val myAppContext = context.applicationContext as? MyApplication ?: return null
            return myAppContext.activityInjector
        }
    }
}
