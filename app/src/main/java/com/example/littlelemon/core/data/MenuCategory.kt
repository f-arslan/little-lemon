package com.example.littlelemon.core.data

import kotlinx.serialization.Serializable

@Serializable
data class MenuCategory(
    val menu: List<MenuNetwork>
)
