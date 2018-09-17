package br.com.vp.memberapp.components

import br.com.vp.memberapp.modules.DateAndTimeModule
import br.com.vp.memberapp.MainActivity
import br.com.vp.memberapp.WelcomeActivity
import br.com.vp.memberapp.modules.MemberDataModule
import br.com.vp.memberapp.modules.MessagesModule
import dagger.Component
import javax.inject.Singleton

/**
 * @author diegovidal on 04/06/2018.
 */

@Singleton
@Component(modules = [
    MemberDataModule::class,
    DateAndTimeModule::class
])
interface MemberAppComponent {

    fun inject(mainActivity: MainActivity)

    fun newWelcomeActivityComponent(): WelcomeActivityComponent
}