package com.example.restaurantfindkun.data.api

import com.example.restaurantfindkun.data.api.response.RestaurantItemListResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {
    @GET("gourmet/v1/")
    fun loadRepos(
        @Query("key") key: String = "918c39bfebeeacb6",
        @Query("large_area") largeArea: String = "Z011",
    ): Call<RestaurantItemListResponse>
}