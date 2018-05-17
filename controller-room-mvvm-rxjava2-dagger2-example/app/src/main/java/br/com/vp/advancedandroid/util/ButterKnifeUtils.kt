package br.com.vp.advancedandroid.util

import butterknife.Unbinder
import timber.log.Timber

/**
 * @author diegovidal on 10/05/2018.
 */
object ButterKnifeUtils {

    fun unbind(unbinder: Unbinder?){

        try {
            unbinder?.unbind()
        } catch (e: IllegalStateException){
            Timber.e(e, "Error unbinding views")
        }
    }
}