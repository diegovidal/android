package br.com.vp.memberapp

import android.app.Application

/**
 * @author diegovidal on 04/06/2018.
 */
class MyApplication: Application() {

    private lateinit var memberAppComponent: MemberAppComponent

    override fun onCreate() {
        super.onCreate()

        sharedInstance = this

        memberAppComponent = DaggerMemberAppComponent
                .builder()
                .build()

    }

    companion object {

        lateinit var sharedInstance: MyApplication

        fun getMemberAppComponent(): MemberAppComponent{
            return sharedInstance.memberAppComponent
        }
    }
}