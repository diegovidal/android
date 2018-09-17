package br.com.vp.advancedandroid.ui

import android.support.v4.app.Fragment


/**
 * @author diegovidal on 10/05/2018.
 */

interface ScreenProvider {

    fun initialScreen(): Fragment
}