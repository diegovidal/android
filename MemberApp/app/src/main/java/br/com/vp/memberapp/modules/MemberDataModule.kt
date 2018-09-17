package br.com.vp.memberapp.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.vp.memberapp.models.NetworkManager
import br.com.vp.memberapp.models.MemberDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author diegovidal on 04/06/2018.
 */

@Module
class MemberDataModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context{
        return context
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {

        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideNetworkManager(): NetworkManager {

        return NetworkManager()
    }

    @Singleton
    @Named("local")
    @Provides
    fun provideMemberDataManagerLocal(sharedPreferences: SharedPreferences): MemberDataManager {

        return MemberDataManager(sharedPreferences)
    }

    @Singleton
    @Named("online")
    @Provides
    fun provideMemberDataManagerOnline(sharedPreferences: SharedPreferences, networkManager: NetworkManager)
            : MemberDataManager {

        return MemberDataManager(sharedPreferences, networkManager)
    }
}