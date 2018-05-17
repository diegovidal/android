package br.com.vp.advancedandroid.database.favorites

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author diegovidal on 16/05/2018.
 */

@Entity
class FavoriteContributor(@PrimaryKey val id: Long)