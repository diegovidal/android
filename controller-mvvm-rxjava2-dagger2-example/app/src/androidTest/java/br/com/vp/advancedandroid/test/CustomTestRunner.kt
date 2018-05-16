package br.com.vp.advancedandroid.test

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import br.com.vp.advancedandroid.base.TestApplication
import kotlin.reflect.jvm.jvmName

/**
 * @author diegovidal on 30/04/2018.
 */
class CustomTestRunner: AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {

        return super.newApplication(cl, TestApplication::class.jvmName, context)
    }
}