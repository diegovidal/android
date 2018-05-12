package br.com.vp.poweradapter.item

import dagger.MapKey

/**
 * @author diegovidal on 11/05/2018.
 */

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class RenderKey(val value: String)