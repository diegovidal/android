package br.com.vp.puremvp.welcome

import br.com.vp.puremvp.data.UserDataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author diegovidal on 12/06/2018.
 */

@Module
class WelcomeModule {

    @Singleton
    @Provides
    fun provideWelcomeContractModel(): WelcomeContract.Model {

        return UserDataManager()
    }

    @Singleton
    @Provides
    fun provideWelcomeContractPresenter(model: WelcomeContract.Model): WelcomeContract.Presenter {

        return WelcomePresenter(model)
    }
}