package br.com.vp.memberapp

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author diegovidal on 04/06/2018.
 */

@Module
object MemberDataModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideMemberDataManager(): MemberDataManager {

        return MemberDataManager()
    }
}