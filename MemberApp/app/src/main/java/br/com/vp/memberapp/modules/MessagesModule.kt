package br.com.vp.memberapp.modules

import br.com.vp.memberapp.models.MessageGenerator
import br.com.vp.memberapp.scope.WelcomeActivityScope
import dagger.Module
import dagger.Provides

/**
 * @author diegovidal on 11/06/2018.
 */

@Module
class MessagesModule {

    @WelcomeActivityScope
    @Provides
    fun provideMessageGenerator(): MessageGenerator {

        return MessageGenerator()
    }
}