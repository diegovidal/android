package br.com.vp.habittrainer.db

import android.provider.BaseColumns

/**
 * @author diegovidal on 23/03/2018.
 */

val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION = 10

object HabitEntry: BaseColumns {

    val TABLE_NAME = "habit"

    val _ID = "id"
    val TITLE_COL = "title"
    val DESC_COL = "description"
    val IMAGE_COL = "image"
}