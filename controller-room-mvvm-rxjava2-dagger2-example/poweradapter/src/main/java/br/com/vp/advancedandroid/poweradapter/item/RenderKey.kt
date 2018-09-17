package br.com.vp.advancedandroid.poweradapter.item

import dagger.MapKey

/**
 * @author diegovidal on 11/05/2018.
 */


@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class RenderKey(val value: String)
