package com.ydh.todoapprooom.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun dao(): TodoDAO

    companion object {
        @Volatile
        private lateinit var localDatabase: LocalDB

        private const val DATABASE_NAME = "local_todo.db"

        fun getDatabase(context: Context): LocalDB {
            if (!this::localDatabase.isInitialized) {
                localDatabase = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDB::class.java,
                    DATABASE_NAME
                ).build()
            }

            return localDatabase
        }
    }
}