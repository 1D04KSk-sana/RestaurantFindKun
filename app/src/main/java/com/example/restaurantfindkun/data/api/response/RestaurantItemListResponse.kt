package com.example.restaurantfindkun.data.api.response

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "results", strict = false)
data class RestaurantItemListResponse(
    @field:ElementList(name = "shop", inline = true)
    var shops: List<Shop>? = null
)