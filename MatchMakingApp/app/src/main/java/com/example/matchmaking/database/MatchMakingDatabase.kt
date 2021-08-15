package com.example.matchmaking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matchmaking.constants.DatabaseConstants
import com.example.matchmaking.features.matchmaking.db.dao.UserDao
import com.example.matchmaking.features.matchmaking.db.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class MatchMakingDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    companion object {
        @Volatile
        private var INSTANCE: MatchMakingDatabase? = null

        fun getDatabase(context: Context): MatchMakingDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchMakingDatabase::class.java,
                    DatabaseConstants.DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}