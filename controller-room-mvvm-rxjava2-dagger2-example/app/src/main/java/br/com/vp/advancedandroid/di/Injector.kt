package br.com.vp.advancedandroid.di

import android.app.Activity
import com.bluelinelabs.conductor.Controller

/**
 * @author diegovidal on 11/04/2018.
 */

object Injector {

    fun inject(activity: Activity) {
        ActivityInjector.get(activity)?.inject(activity)
    }

    fun clearComponent(activity: Activity) {
        ActivityInjector.get(activity)?.clear(activity)
    }

    fun inject(controller: Controller){

        controller.activity?.let {
            ScreenInjector.get(it)?.inject(controller)
        }
    }

    fun clearComponent(controller: Controller) {

        controller.activity?.let {
            ScreenInjector.get(it)?.clear(controller)
        }
    }
}