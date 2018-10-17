package com.dvidal.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.dvidal.cache.db.ProjectConstants

/**
 * @author diegovidal on 01/10/2018.
 */

@Entity(tableName = ProjectConstants.TABLE_NAME)
data class CachedProject(
        @PrimaryKey @ColumnInfo(name = ProjectConstants.COLUMN_PROJECT_ID) var id: String,
        var name: String,
        var fullName: String,
        var starCount: String,
        var dateCreated: String,
        var ownerName: String,
        var ownerAvatar: String,
        @ColumnInfo(name = ProjectConstants.COLUMN_IS_BOOKMARKED) var isBookmarked: Boolean
)