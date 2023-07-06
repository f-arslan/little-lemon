package com.example.littlelemon.core.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.littlelemon.core.data.MenuNetworkRoom

@Dao
interface LittleLemonDao {
    @Insert
    suspend fun saveFullMenu(menu: List<MenuNetworkRoom>)

    @Query("SELECT * FROM MenuNetworkRoom")
    suspend fun getMenu(): List<MenuNetworkRoom>

    @Query("SELECT (SELECT COUNT(*) FROM MenuNetworkRoom) == 0")
    fun isEmpty(): Boolean
}