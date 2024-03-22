package com.example.restaurantfindkun.data.api.response

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "results", strict = false)
data class RestaurantItemListResponse(
    @field:ElementList(name = "shop", inline = true)
    var shops: List<Shop>? = null
)