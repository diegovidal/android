package br.com.vp.advancedandroid.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author diegovidal on 16/05/2018.
 */

@Module
object DatabaseModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase{
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "favorites_database").build()
    }
}