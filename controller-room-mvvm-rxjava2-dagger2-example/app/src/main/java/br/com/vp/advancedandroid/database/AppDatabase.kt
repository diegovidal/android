package br.com.vp.advancedandroid.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.vp.advancedandroid.database.favorites.FavoriteContributor
import br.com.vp.advancedandroid.database.favorites.FavoriteContributorDao

/**
 * @author diegovidal on 16/05/2018.
 */

@Database(entities = [
    FavoriteContributor::class
], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favoriteContributorDao(): FavoriteContributorDao
}