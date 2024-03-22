package com.example.restaurantfindkun.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class SearchItemList(
    val latitude: String,
    val longitude: String,
    val range: String
)
