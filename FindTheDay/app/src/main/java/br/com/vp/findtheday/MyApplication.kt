package br.com.vp.findtheday

import android.app.Application
import br.com.vp.findtheday.di.DaggerMyApplicationComponent
import br.com.vp.findtheday.di.MyApplicationComponent

/**
 * @author diegovidal on 05/06/2018.
 */
class MyApplication: Application() {

    lateinit var myApplicationComponent: MyApplicationComponent

    override fun onCreate() {
        super.onCreate()

        sharedInstance = this
        myApplicationComponent = DaggerMyApplicationComponent
                .builder().build()
    }

    companion object {

        lateinit var sharedInstance: MyApplication

        fun getComponent(): MyApplicationComponent {
            return sharedInstance.myApplicationComponent
        }
    }
}