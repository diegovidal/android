package br.com.vp.advancedandroid.ui

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router

/**
 * @author diegovidal on 23/04/2018.
 */

interface ScreenNavigator {

    fun initWithRouter(router: Router, rootScreen: Controller)
    fun pop(): Boolean
    fun clear()
}