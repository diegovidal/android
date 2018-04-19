package br.com.vp.advancedandroid.base

import android.content.Context
import br.com.vp.advancedandroid.di.Injector
import com.bluelinelabs.conductor.Controller

/**
 * @author diegovidal on 18/04/2018.
 */
 abstract class BaseController: Controller() {

    private var injected = false

    override fun onContextAvailable(context: Context) {

        // Controller instances are retained across config changes, so this method can be called more than once. This makes
        // sure we don't wast any time injecting more than once, though technically it would't change functionality
        if (!injected){
            Injector.inject(this)
            injected = true
        }

        super.onContextAvailable(context)
    }
}