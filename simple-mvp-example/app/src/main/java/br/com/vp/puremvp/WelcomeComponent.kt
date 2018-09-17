package br.com.vp.puremvp

import br.com.vp.puremvp.welcome.WelcomeActivity
import br.com.vp.puremvp.welcome.WelcomeModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author diegovidal on 12/06/2018.
 */

@Singleton
@Component(modules = [
    WelcomeModule::class
])
interface WelcomeComponent {

    fun inject(welcomeActivity: WelcomeActivity)
}