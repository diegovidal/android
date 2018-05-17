package br.com.vp.advancedandroid.base

import android.support.test.InstrumentationRegistry

/**
 * @author diegovidal on 30/04/2018.
 */
class TestApplication: MyApplication() {

    override fun initComponent(): ApplicationComponent {

        return DaggerTestApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    companion object {

        fun getComponent(): TestApplicationComponent? {

            return (InstrumentationRegistry.getTargetContext().applicationContext as? TestApplication)?.component as? TestApplicationComponent
        }
    }
}