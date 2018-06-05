package br.com.vp.memberapp

import dagger.Component
import javax.inject.Singleton

/**
 * @author diegovidal on 04/06/2018.
 */

@Singleton
@Component(modules = [
    MemberDataModule::class
])
interface MemberAppComponent {

    fun inject(mainActivity: MainActivity)
}