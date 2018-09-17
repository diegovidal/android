package br.com.vp.advancedandroid.database.favorites

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * @author diegovidal on 16/05/2018.
 */

@Dao
interface FavoriteContributorDao {

    @Query("SELECT * FROM favoritecontributor")
    fun getFavoritedContributors(): Flowable<List<FavoriteContributor>>

    @Insert
    fun addFavorite(contributor: FavoriteContributor)

    @Delete
    fun deleteFavorite(contributor: FavoriteContributor)
}