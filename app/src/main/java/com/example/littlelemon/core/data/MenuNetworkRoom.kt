package com.example.littlelemon.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuNetworkRoom(
    @PrimaryKey val id: Int = -1,
    val title: String = "",
    val description: String = "",
    val price: String = "",
    val image: String = "",
    val category: String = ""
)
