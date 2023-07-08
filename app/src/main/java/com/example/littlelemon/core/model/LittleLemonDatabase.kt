package com.example.littlelemon.core.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.littlelemon.core.data.MenuNetworkRoom

@Database(entities = [MenuNetworkRoom::class], version = 1, exportSchema = false)
abstract class LittleLemonDatabase : RoomDatabase() {
    abstract fun LittleLemonDao(): LittleLemonDao

    companion object {
        @Volatile
        private var INSTANCE: LittleLemonDatabase? = null

        fun getInstance(context: Context): LittleLemonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LittleLemonDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}