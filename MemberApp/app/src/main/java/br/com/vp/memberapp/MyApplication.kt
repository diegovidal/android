package br.com.vp.memberapp

import android.app.Application
import br.com.vp.memberapp.components.DaggerMemberAppComponent
import br.com.vp.memberapp.components.MemberAppComponent
import br.com.vp.memberapp.modules.MemberDataModule

/**
 * @author diegovidal on 04/06/2018.
 */
class MyApplication: Application() {

    private lateinit var memberAppComponent: MemberAppComponent
//    private lateinit var wellcomeActivityComponent: WelcomeActivityComponent

    override fun onCreate() {
        super.onCreate()

        sharedInstance = this

        memberAppComponent = DaggerMemberAppComponent
                .builder()
                .memberDataModule(MemberDataModule(applicationContext))
                .build()

//        wellcomeActivityComponent = DaggerWelcomeActivityComponent
//                .builder()
//                .memberAppComponent(memberAppComponent)
//                .build()

    }

    companion object {

        lateinit var sharedInstance: MyApplication

        fun getMemberAppComponent(): MemberAppComponent {
            return sharedInstance.memberAppComponent
        }

//        fun getWellcomeActivityComponent(): WelcomeActivityComponent {
//            return sharedInstance.wellcomeActivityComponent
//        }
    }
}