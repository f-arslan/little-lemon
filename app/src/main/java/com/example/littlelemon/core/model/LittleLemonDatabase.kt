package com.example.littlelemon.core.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.littlelemon.core.data.MenuNetworkRoom

@Database(entities = [MenuNetworkRoom::class], version = 1, exportSchema = false)
abstract class LittleLemonDatabase: RoomDatabase() {
    abstract fun LittleLemonDao(): LittleLemonDao
}