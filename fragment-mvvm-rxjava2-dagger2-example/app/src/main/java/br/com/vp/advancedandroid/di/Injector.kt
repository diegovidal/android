package br.com.vp.advancedandroid.di

import android.app.Activity
import android.support.v4.app.Fragment

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

    fun inject(fragment: Fragment){

        fragment.activity?.let {
            ScreenInjector.get(it)?.inject(fragment)
        }
    }

    fun clearComponent(fragment: Fragment) {

        fragment.activity?.let {
            ScreenInjector.get(it)?.clear(fragment)
        }
    }
}