package com.example.restaurantfindkun.data.api

import com.example.restaurantfindkun.data.api.response.RestaurantItemListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//
//APIで動かすときに使う
//
interface RestaurantService {
    @GET("gourmet/v1/")
    fun loadRepos(
        @Query("key") key: String = "918c39bfebeeacb6",
        @Query("id") id: String?,
        @Query("large_area") largeArea: String?,
        @Query("lat") latitude: String?,
        @Query("lng") longitude: String?,
        @Query("range") range: String?,
    ): Call<RestaurantItemListResponse>
}