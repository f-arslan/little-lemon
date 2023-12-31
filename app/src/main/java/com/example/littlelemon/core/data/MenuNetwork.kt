package com.example.littlelemon.core.data

import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val price: String = "",
    val image: String = "",
    val category: String = ""
) {
    fun toMenuNetworkRoom() = MenuNetworkRoom(
        id = id,
        title = title,
        description = description,
        price = price,
        image = image,
        category = category
    )
}