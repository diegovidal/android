package com.dvidal.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dvidal.cache.dao.CachedProjecstDao
import com.dvidal.cache.dao.ConfigDao
import com.dvidal.cache.model.CachedProject
import com.dvidal.cache.model.Config
import javax.inject.Inject

/**
 * @author diegovidal on 01/10/2018.
 */
@Database(
        entities = [CachedProject::class, Config::class],
        version = 1
)
abstract class ProjectsDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjecstDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: ProjectsDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): ProjectsDatabase {

            if (INSTANCE == null){
                synchronized(lock) {
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                ProjectsDatabase::class.java, "projects.db")
                                .build()
                    }
                    return INSTANCE as ProjectsDatabase
                }
            }
            return INSTANCE as ProjectsDatabase
        }
    }
}