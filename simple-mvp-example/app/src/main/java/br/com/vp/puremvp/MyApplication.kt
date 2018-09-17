package br.com.vp.puremvp

import android.app.Application
import br.com.vp.puremvp.welcome.WelcomeModule

/**
 * @author diegovidal on 12/06/2018.
 */
class MyApplication: Application() {

    private lateinit var welcomeComponent: WelcomeComponent

    override fun onCreate() {
        super.onCreate()

        sharedInstance = this
        welcomeComponent = DaggerWelcomeComponent.builder()
                .welcomeModule(WelcomeModule())
                .build()

    }

    companion object {

        lateinit var sharedInstance: MyApplication

        fun getWelcomeComponent(): WelcomeComponent {
            return sharedInstance.welcomeComponent
        }
    }
}