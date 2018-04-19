package br.com.vp.advancedandroid.di

import com.bluelinelabs.conductor.Controller
import dagger.MapKey
import java.lang.annotation.ElementType
import kotlin.reflect.KClass

/**
 * @author diegovidal on 18/04/2018.
 */

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ControllerKey(val value: KClass<out Controller>)