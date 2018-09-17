package br.com.vp.memberapp.components

import br.com.vp.memberapp.WelcomeActivity
import br.com.vp.memberapp.modules.MessagesModule
import br.com.vp.memberapp.scope.WelcomeActivityScope
import dagger.Component
import dagger.Subcomponent

/**
 * @author diegovidal on 11/06/2018.
 */

//@WelcomeActivityScope
//@Component(dependencies = [MemberAppComponent::class],
//        modules = [MessagesModule::class])
//interface WelcomeActivityComponent {
//
//    fun inject(welcomeActivity: WelcomeActivity)
//}

@WelcomeActivityScope
@Subcomponent(modules = [
    MessagesModule::class
])
interface WelcomeActivityComponent {

    fun inject(welcomeActivity: WelcomeActivity)
}