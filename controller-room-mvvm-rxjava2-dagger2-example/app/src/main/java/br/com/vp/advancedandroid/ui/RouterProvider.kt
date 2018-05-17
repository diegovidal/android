package br.com.vp.advancedandroid.ui

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router

/**
 * @author diegovidal on 10/05/2018.
 */

interface RouterProvider {

    fun getRouter(): Router

    fun initialScreen(): Controller
}