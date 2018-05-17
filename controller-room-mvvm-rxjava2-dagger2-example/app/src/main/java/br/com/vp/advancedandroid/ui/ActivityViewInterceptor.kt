package br.com.vp.advancedandroid.ui

import android.app.Activity
import android.support.annotation.LayoutRes

/**
 * @author diegovidal on 09/05/2018.
 */
interface ActivityViewInterceptor {

    fun setContentView(activity: Activity, @LayoutRes layoutRes: Int)

    fun clear()

    companion object {

        var DEFAULT: ActivityViewInterceptor = object : ActivityViewInterceptor {

            override fun setContentView(activity: Activity, layoutRes: Int) {

                activity.setContentView(layoutRes)
            }

            override fun clear() {


            }
        }
    }
}