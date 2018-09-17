package br.com.vp.memberapp.modules

import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author diegovidal on 11/06/2018.
 */

@Module
class DateAndTimeModule {

    @Singleton
    @Provides
    fun provideCalendar(): Calendar {
        return Calendar.getInstance()
    }

    @Singleton
    @Named("dd-MMM-yyyy")
    @Provides
    fun provideDateType1(calendar: Calendar): String {

        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        return df.format(calendar.time)
    }

    @Singleton
    @Named("dd-MMM-yy")
    @Provides
    fun provideDateType2(calendar: Calendar): String {

        val df = SimpleDateFormat("dd-MMM-yy", Locale.getDefault())
        return df.format(calendar.time)

    }

    @Singleton
    @Named("yyyy-MM-dd HH:mm:ss")
    @Provides
    fun provideDateType3(calendar: Calendar): String {

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return df.format(calendar.time)
    }
}