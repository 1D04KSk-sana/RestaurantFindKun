package com.example.restaurantfindkun.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class RestaurantItemList(
    val id: String,
    val name: String,
    val smallArea: String,
    val catch: String
)
